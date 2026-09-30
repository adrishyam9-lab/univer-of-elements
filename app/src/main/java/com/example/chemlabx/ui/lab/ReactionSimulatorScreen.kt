package com.example.chemlabx.ui.lab

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.ReactionData
import com.example.chemlabx.data.model.Reaction
import com.example.chemlabx.ui.components.ChemLabTopBar
import kotlin.math.sin

@Composable
fun ReactionSimulatorScreen(
    initialReactionId: String? = null,
    onSearchClick: () -> Unit = {}
) {
    val allReactions = remember { ReactionData.reactions }
    var selectedReaction by remember {
        mutableStateOf(
            if (!initialReactionId.isNullOrBlank()) {
                allReactions.firstOrNull { it.id == initialReactionId } ?: allReactions.first()
            } else allReactions.first()
        )
    }

    var currentStageIndex by remember { mutableIntStateOf(0) }
    var isPlaying by remember { mutableStateOf(true) }
    var isSlowMotion by remember { mutableStateOf(false) }
    var temperature by remember { mutableFloatStateOf(25.0f) }
    var concentration by remember { mutableFloatStateOf(1.0f) }

    val scrollState = rememberScrollState()

    // Molecular animation progress
    val duration = if (isSlowMotion) 5000 else 2500
    val infiniteTransition = rememberInfiniteTransition(label = "mol_reaction")
    val animProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing)
        ),
        label = "anim_progress"
    )

    val currentStage = selectedReaction.stages.getOrElse(currentStageIndex) { selectedReaction.stages.first() }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_reactions"),
                onSearchClick = onSearchClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Reaction Selector Carousel
            Text(
                text = "Select Reaction Model",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(allReactions) { r ->
                    FilterChip(
                        selected = selectedReaction.id == r.id,
                        onClick = {
                            selectedReaction = r
                            currentStageIndex = 0
                        },
                        label = { Text(r.name, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.testTag("reaction_chip_${r.id}")
                    )
                }
            }

            // Balanced Chemical Equation Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = LocalizationManager.getString("balanced_equation"),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF94A3B8)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = selectedReaction.equation,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Type: ${selectedReaction.reactionType}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFF59E0B)
                    )
                }
            }

            // Interactive Molecular Level Animation Stage Canvas
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Stage Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Stage ${currentStageIndex + 1} of ${selectedReaction.stages.size}: ${currentStage.title}",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )
                            Text(
                                text = currentStage.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0xFF1E293B)
                        ) {
                            Text(
                                text = currentStage.visualState,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF38BDF8),
                                fontSize = 9.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Reaction Canvas (Reactants -> Collision -> Bond Formation -> Products)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(Color(0xFF1E293B), Color(0xFF090D16))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val centerX = size.width / 2f
                            val centerY = size.height / 2f
                            val t = if (isPlaying) animProgress else 0.5f

                            when (currentStageIndex) {
                                0 -> {
                                    // Stage 1: APPROACH — Reactants moving toward each other
                                    val leftMolX = centerX * 0.4f + (centerX * 0.35f * t)
                                    val rightMolX = size.width * 0.8f - (centerX * 0.35f * t)

                                    // Molecule A (e.g. Acid / Reactant 1)
                                    drawCircle(Color(0xFF38BDF8), radius = 22.dp.toPx(), center = Offset(leftMolX, centerY))
                                    drawCircle(Color(0xFFF8FAFC), radius = 12.dp.toPx(), center = Offset(leftMolX - 16.dp.toPx(), centerY))

                                    // Molecule B (e.g. Base / Reactant 2)
                                    drawCircle(Color(0xFFEF4444), radius = 22.dp.toPx(), center = Offset(rightMolX, centerY))
                                    drawCircle(Color(0xFF8B5CF6), radius = 16.dp.toPx(), center = Offset(rightMolX + 18.dp.toPx(), centerY))
                                }
                                1 -> {
                                    // Stage 2: COLLISION & ACTIVATION — Transition state / Bond stretching
                                    val vibration = (sin(t * 20 * Math.PI) * 4.dp.toPx()).toFloat()

                                    // Collision energy shockwave
                                    drawCircle(
                                        color = Color(0xFFF59E0B).copy(alpha = 0.35f),
                                        radius = (40.dp.toPx() + (t * 25.dp.toPx())),
                                        center = Offset(centerX, centerY),
                                        style = Stroke(width = 2.dp.toPx())
                                    )

                                    // Reactants in direct collision contact
                                    drawCircle(Color(0xFF38BDF8), radius = 22.dp.toPx(), center = Offset(centerX - 18.dp.toPx() + vibration, centerY))
                                    drawCircle(Color(0xFFEF4444), radius = 22.dp.toPx(), center = Offset(centerX + 18.dp.toPx() - vibration, centerY))

                                    // Breaking bond line dashed
                                    drawLine(
                                        color = Color(0xFFF59E0B),
                                        start = Offset(centerX - 18.dp.toPx(), centerY),
                                        end = Offset(centerX + 18.dp.toPx(), centerY),
                                        strokeWidth = 3.dp.toPx()
                                    )
                                }
                                2 -> {
                                    // Stage 3: BOND REORGANIZATION — Electron redistribution
                                    val electronX = centerX - 15.dp.toPx() + (30.dp.toPx() * t)

                                    drawCircle(Color(0xFF38BDF8).copy(alpha = 0.5f), radius = 30.dp.toPx(), center = Offset(centerX - 24.dp.toPx(), centerY))
                                    drawCircle(Color(0xFFEF4444).copy(alpha = 0.5f), radius = 30.dp.toPx(), center = Offset(centerX + 24.dp.toPx(), centerY))

                                    // Traveling electron packet
                                    drawCircle(Color(0xFFFBBF24), radius = 7.dp.toPx(), center = Offset(electronX, centerY))
                                }
                                3 -> {
                                    // Stage 4: PRODUCTS STABILIZED — Final separated product molecules
                                    val leftProductX = centerX * 0.35f
                                    val rightProductX = size.width * 0.72f

                                    // Product 1 (e.g. H2O)
                                    drawCircle(Color(0xFFEF4444), radius = 20.dp.toPx(), center = Offset(leftProductX, centerY))
                                    drawCircle(Color(0xFFF8FAFC), radius = 10.dp.toPx(), center = Offset(leftProductX - 14.dp.toPx(), centerY + 10.dp.toPx()))
                                    drawCircle(Color(0xFFF8FAFC), radius = 10.dp.toPx(), center = Offset(leftProductX + 14.dp.toPx(), centerY + 10.dp.toPx()))

                                    // Product 2 (e.g. Salt ion / gas molecule)
                                    drawCircle(Color(0xFF8B5CF6), radius = 18.dp.toPx(), center = Offset(rightProductX - 15.dp.toPx(), centerY))
                                    drawCircle(Color(0xFF10B981), radius = 22.dp.toPx(), center = Offset(rightProductX + 15.dp.toPx(), centerY))
                                }
                            }
                        }

                        Text(
                            text = currentStage.atomicDescription,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF94A3B8),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Player Controls (Play, Pause, Step Next, Step Prev, Replay, Slow Motion)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            FilledIconButton(
                                onClick = { currentStageIndex = (currentStageIndex - 1).coerceAtLeast(0) },
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF1E293B)),
                                enabled = currentStageIndex > 0,
                                modifier = Modifier.testTag("reaction_prev_button")
                            ) {
                                Icon(Icons.Default.FastRewind, contentDescription = "Previous Stage", tint = Color.White)
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            FilledIconButton(
                                onClick = { isPlaying = !isPlaying },
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.testTag("reaction_play_pause_button")
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = "Play/Pause",
                                    tint = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            FilledIconButton(
                                onClick = {
                                    currentStageIndex = (currentStageIndex + 1).coerceAtMost(selectedReaction.stages.lastIndex)
                                },
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF1E293B)),
                                enabled = currentStageIndex < selectedReaction.stages.lastIndex,
                                modifier = Modifier.testTag("reaction_next_button")
                            ) {
                                Icon(Icons.Default.FastForward, contentDescription = "Next Stage", tint = Color.White)
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            FilledIconButton(
                                onClick = {
                                    currentStageIndex = 0
                                    isPlaying = true
                                },
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF1E293B)),
                                modifier = Modifier.testTag("reaction_replay_button")
                            ) {
                                Icon(Icons.Default.Replay, contentDescription = "Replay", tint = Color.White)
                            }
                        }

                        // Slow Motion Toggle
                        FilledIconButton(
                            onClick = { isSlowMotion = !isSlowMotion },
                            shape = CircleShape,
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = if (isSlowMotion) Color(0xFFF59E0B) else Color(0xFF1E293B)
                            ),
                            modifier = Modifier.testTag("reaction_slow_mo_button")
                        ) {
                            Icon(Icons.Default.SlowMotionVideo, contentDescription = "Slow Motion", tint = if (isSlowMotion) Color.Black else Color.White)
                        }
                    }
                }
            }

            // Reaction Conditions Controls (Temperature, Concentration)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Reaction Conditions",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Temperature: ${temperature.toInt()} °C", style = MaterialTheme.typography.bodySmall, color = Color(0xFFF59E0B))
                        Text(if (temperature > 50) "Accelerated Collision Rate" else "Standard Kinetic Energy", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                    }
                    Slider(
                        value = temperature,
                        onValueChange = { temperature = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Concentration: ${String.format("%.1f", concentration)} M", style = MaterialTheme.typography.bodySmall, color = Color(0xFF38BDF8))
                        Text(if (concentration > 1.2) "High Collision Density" else "Standard Molarity", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                    }
                    Slider(
                        value = concentration,
                        onValueChange = { concentration = it },
                        valueRange = 0.1f..3.0f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // "WHY DID THIS REACTION HAPPEN?" SECTION (MANDATORY REQUIREMENT)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(Color(0xFF10B981), Color(0xFF06B6D4))))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF10B981).copy(alpha = 0.2f),
                            modifier = Modifier.size(28.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("?", color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = LocalizationManager.getString("why_reaction_happened"),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF10B981)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Fundamental Thermodynamic / Chemical Driving Force
                    Text(
                        text = selectedReaction.whyItHappens,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFF1F5F9),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Atomic Level Subsection
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "🔬 " + LocalizationManager.getString("atomic_level"),
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = selectedReaction.atomicLevel,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFE2E8F0),
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Macroscopic Level Subsection
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "👁️ " + LocalizationManager.getString("macroscopic_level"),
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF59E0B)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = selectedReaction.macroscopicLevel,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFE2E8F0),
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Thermodynamic Profile (ΔH)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "⚡ " + LocalizationManager.getString("thermodynamic_profile"),
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFA78BFA)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = selectedReaction.energyChange,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                color = Color(0xFFF8FAFC)
                            )
                        }
                    }
                }
            }

            // Safety Warning Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF241416))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Warning, contentDescription = "Safety", tint = Color(0xFFEF4444))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Laboratory Safety & Handling Protocol",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFEF4444)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = selectedReaction.safetyWarning,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFECACA),
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
