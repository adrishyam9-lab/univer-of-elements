package com.example.chemlabx.ui.lab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import kotlin.random.Random

data class LabSubstance(
    val id: String,
    val name: String,
    val formula: String,
    val type: String, // Acid, Base, Salt, Metal, Indicator, Solvent
    val baseColor: Color
)

@Composable
fun VirtualLabScreen(
    initialSubstance: String? = null,
    onSearchClick: () -> Unit = {},
    onViewReactionDetail: (String) -> Unit = {}
) {
    // Substances Rack
    val availableSubstances = remember {
        listOf(
            LabSubstance("HCl", "Hydrochloric Acid", "HCl(aq)", "Acid", Color(0x3338BDF8)),
            LabSubstance("NaOH", "Sodium Hydroxide", "NaOH(aq)", "Base", Color(0x33A78BFA)),
            LabSubstance("H2SO4", "Sulfuric Acid", "H₂SO₄(aq)", "Acid", Color(0x44FBBF24)),
            LabSubstance("CH3COOH", "Acetic Acid (Vinegar)", "CH₃COOH(aq)", "Acid", Color(0x22F8FAFC)),
            LabSubstance("KI", "Potassium Iodide", "KI(aq)", "Salt", Color(0x22FFFFFF)),
            LabSubstance("Pb(NO3)2", "Lead(II) Nitrate", "Pb(NO₃)₂(aq)", "Salt", Color(0x22FFFFFF)),
            LabSubstance("CuSO4", "Copper(II) Sulfate", "CuSO₄(aq)", "Salt", Color(0xAA0284C7)),
            LabSubstance("BaCl2", "Barium Chloride", "BaCl₂(aq)", "Salt", Color(0x22FFFFFF)),
            LabSubstance("Na2SO4", "Sodium Sulfate", "Na₂SO₄(aq)", "Salt", Color(0x22FFFFFF)),
            LabSubstance("NaHCO3", "Sodium Bicarbonate", "NaHCO₃(s)", "Salt", Color(0x44FFFFFF)),
            LabSubstance("Zn", "Zinc Granules", "Zn(s)", "Metal", Color(0xFF64748B)),
            LabSubstance("Fe", "Iron Metal / Nail", "Fe(s)", "Metal", Color(0xFF475569)),
            LabSubstance("H2O2", "Hydrogen Peroxide", "H₂O₂(aq)", "Oxide", Color(0x22FFFFFF)),
            LabSubstance("MnO2", "Manganese Dioxide (Catalyst)", "MnO₂(s)", "Catalyst", Color(0xFF1E293B)),
            LabSubstance("C2H5OH", "Ethanol", "C₂H₅OH(l)", "Solvent", Color(0x15FFFFFF)),
            LabSubstance("Phenolphthalein", "Phenolphthalein Indicator", "PhIn", "Indicator", Color(0x15FFFFFF)),
            LabSubstance("UniversalIndicator", "Universal Indicator", "UI", "Indicator", Color(0x9922C55E))
        )
    }

    val addedSubstances = remember {
        mutableStateListOf<LabSubstance>().apply {
            if (!initialSubstance.isNullOrBlank()) {
                val match = availableSubstances.firstOrNull {
                    it.id.equals(initialSubstance, true) || it.name.contains(initialSubstance, true)
                }
                if (match != null) add(match)
            }
        }
    }

    var isBurnerOn by remember { mutableStateOf(false) }
    var temperature by remember { mutableFloatStateOf(22.0f) }
    var isStirrerOn by remember { mutableStateOf(false) }
    var showSafetyWarning by remember { mutableStateOf<String?>(null) }
    var selectedEquipment by remember { mutableStateOf("Beaker 250ml") }
    var activeReaction by remember { mutableStateOf<Reaction?>(null) }
    var reactionRunCount by remember { mutableStateOf(0) }

    val scrollState = rememberScrollState()

    // Dynamic fluid color & reaction detection
    val fluidColor = remember(addedSubstances.toList(), activeReaction, isBurnerOn) {
        val ids = addedSubstances.map { it.id }
        when {
            ids.contains("Pb(NO3)2") && ids.contains("KI") -> Color(0xFFFACC15) // Golden Rain PbI2
            ids.contains("HCl") && ids.contains("NaOH") && ids.contains("Phenolphthalein") -> Color(0xFFF43F5E).copy(alpha = 0.6f) // Magenta endpoint
            ids.contains("BaCl2") && ids.contains("Na2SO4") -> Color(0xFFF8FAFC).copy(alpha = 0.85f) // Milky white BaSO4
            ids.contains("CuSO4") -> Color(0xFF0284C7).copy(alpha = 0.75f) // Royal blue
            ids.contains("UniversalIndicator") -> {
                if (ids.contains("HCl") || ids.contains("H2SO4")) Color(0xFFEF4444).copy(alpha = 0.8f) // Red pH 1-2
                else if (ids.contains("NaOH")) Color(0xFF8B5CF6).copy(alpha = 0.8f) // Purple pH 13
                else Color(0xFF10B981).copy(alpha = 0.8f) // Green pH 7
            }
            addedSubstances.isEmpty() -> Color.Transparent
            else -> addedSubstances.last().baseColor
        }
    }

    val isBubbling = remember(addedSubstances.toList(), activeReaction) {
        val ids = addedSubstances.map { it.id }
        (ids.contains("Zn") && (ids.contains("HCl") || ids.contains("H2SO4"))) ||
                (ids.contains("NaHCO3") && (ids.contains("HCl") || ids.contains("CH3COOH"))) ||
                (ids.contains("H2O2") && ids.contains("MnO2"))
    }

    val isPrecipitating = remember(addedSubstances.toList()) {
        val ids = addedSubstances.map { it.id }
        (ids.contains("Pb(NO3)2") && ids.contains("KI")) ||
                (ids.contains("BaCl2") && ids.contains("Na2SO4"))
    }

    // Stirrer vortex & bubbling animation
    val infiniteTransition = rememberInfiniteTransition(label = "lab_physics")
    val bubblePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ),
        label = "bubble_phase"
    )

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("free_chemistry_lab"),
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
            // Virtual Lab Workstation Canvas (Apparatus View)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("lab_workstation_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Apparatus Header & Equipment Selector
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Workstation: $selectedEquipment",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF38BDF8)
                        )

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF1E293B)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Thermostat, contentDescription = "Temp", tint = Color(0xFFF59E0B), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${temperature.toInt()} °C",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFFF59E0B)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Physical Laboratory Apparatus Canvas
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF1E293B), Color(0xFF050811))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val centerX = size.width / 2f
                            val bottomY = size.height - 30.dp.toPx()

                            // 1. Draw Bunsen Burner below if on
                            if (isBurnerOn) {
                                val burnerTopY = bottomY - 10.dp.toPx()
                                // Flame glow
                                drawCircle(
                                    brush = Brush.radialGradient(
                                        colors = listOf(Color(0xFF38BDF8), Color(0xFF3B82F6), Color.Transparent),
                                        center = Offset(centerX, burnerTopY - 14.dp.toPx()),
                                        radius = 35.dp.toPx()
                                    ),
                                    radius = 35.dp.toPx(),
                                    center = Offset(centerX, burnerTopY - 14.dp.toPx())
                                )
                                // Bunsen tube
                                drawRoundRect(
                                    color = Color(0xFF475569),
                                    topLeft = Offset(centerX - 8.dp.toPx(), burnerTopY),
                                    size = Size(16.dp.toPx(), 25.dp.toPx()),
                                    cornerRadius = CornerRadius(2.dp.toPx())
                                )
                            }

                            // 2. Draw Beaker Container Outline
                            val beakerWidth = 140.dp.toPx()
                            val beakerHeight = 160.dp.toPx()
                            val beakerLeft = centerX - beakerWidth / 2f
                            val beakerTop = bottomY - beakerHeight - (if (isBurnerOn) 30.dp.toPx() else 0f)

                            // Glass beaker back glow
                            drawRoundRect(
                                color = Color(0xFF334155).copy(alpha = 0.2f),
                                topLeft = Offset(beakerLeft, beakerTop),
                                size = Size(beakerWidth, beakerHeight),
                                cornerRadius = CornerRadius(14.dp.toPx())
                            )

                            // 3. Draw Liquid contents inside Beaker
                            if (addedSubstances.isNotEmpty()) {
                                val fillPercent = (addedSubstances.size * 0.18f).coerceIn(0.25f, 0.85f)
                                val liquidHeight = beakerHeight * fillPercent
                                val liquidTop = beakerTop + beakerHeight - liquidHeight

                                // Liquid Body
                                drawRoundRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(fluidColor.copy(alpha = 0.8f), fluidColor)
                                    ),
                                    topLeft = Offset(beakerLeft + 4.dp.toPx(), liquidTop),
                                    size = Size(beakerWidth - 8.dp.toPx(), liquidHeight),
                                    cornerRadius = CornerRadius(10.dp.toPx())
                                )

                                // Effervescence / Gas Bubbles
                                if (isBubbling) {
                                    val random = Random(42)
                                    for (i in 0 until 18) {
                                        val bubbleX = beakerLeft + 12.dp.toPx() + (random.nextFloat() * (beakerWidth - 24.dp.toPx()))
                                        val localPhase = (bubblePhase + (i * 0.055f)) % 1f
                                        val bubbleY = beakerTop + beakerHeight - (localPhase * liquidHeight)
                                        drawCircle(
                                            color = Color.White.copy(alpha = 0.75f),
                                            radius = (2.dp.toPx() + (i % 3) * 1.5.dp.toPx()),
                                            center = Offset(bubbleX, bubbleY)
                                        )
                                    }
                                }

                                // Precipitate sediment settling on bottom
                                if (isPrecipitating) {
                                    drawRoundRect(
                                        color = fluidColor,
                                        topLeft = Offset(beakerLeft + 6.dp.toPx(), beakerTop + beakerHeight - 16.dp.toPx()),
                                        size = Size(beakerWidth - 12.dp.toPx(), 14.dp.toPx()),
                                        cornerRadius = CornerRadius(6.dp.toPx())
                                    )
                                }

                                // Magnetic stirrer pill spinning
                                if (isStirrerOn) {
                                    val pillWidth = 26.dp.toPx()
                                    drawRoundRect(
                                        color = Color.White,
                                        topLeft = Offset(centerX - pillWidth / 2f, beakerTop + beakerHeight - 8.dp.toPx()),
                                        size = Size(pillWidth, 5.dp.toPx()),
                                        cornerRadius = CornerRadius(3.dp.toPx())
                                    )
                                }
                            }

                            // 4. Draw Glass Beaker Outer Rim and Wall
                            drawRoundRect(
                                color = Color(0xFF64748B),
                                topLeft = Offset(beakerLeft, beakerTop),
                                size = Size(beakerWidth, beakerHeight),
                                cornerRadius = CornerRadius(14.dp.toPx()),
                                style = Stroke(width = 3.dp.toPx())
                            )

                            // Beaker graduation markings
                            for (tick in 1..4) {
                                val tickY = beakerTop + (beakerHeight * (tick * 0.2f))
                                drawLine(
                                    color = Color(0xFF94A3B8).copy(alpha = 0.5f),
                                    start = Offset(beakerLeft + 6.dp.toPx(), tickY),
                                    end = Offset(beakerLeft + 20.dp.toPx(), tickY),
                                    strokeWidth = 1.5.dp.toPx()
                                )
                            }
                        }

                        // Floating Status Badge over container
                        if (addedSubstances.isEmpty()) {
                            Text(
                                text = "Container Empty\nAdd chemicals below to start experimenting",
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Equipment Control Toggles (Bunsen Burner, Magnetic Stirrer)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Burner toggle
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.LocalFireDepartment,
                                contentDescription = "Burner",
                                tint = if (isBurnerOn) Color(0xFFEF4444) else Color(0xFF64748B)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Bunsen Burner", style = MaterialTheme.typography.labelMedium, color = Color(0xFFE2E8F0))
                            Spacer(modifier = Modifier.width(6.dp))
                            Switch(
                                checked = isBurnerOn,
                                onCheckedChange = {
                                    isBurnerOn = it
                                    if (it) temperature = (temperature + 45f).coerceAtMost(100f)
                                    else temperature = 22f
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFFEF4444),
                                    checkedTrackColor = Color(0xFFEF4444).copy(alpha = 0.4f)
                                ),
                                modifier = Modifier.testTag("burner_switch")
                            )
                        }

                        // Stirrer toggle
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Magnetic Stirrer", style = MaterialTheme.typography.labelMedium, color = Color(0xFFE2E8F0))
                            Spacer(modifier = Modifier.width(6.dp))
                            Switch(
                                checked = isStirrerOn,
                                onCheckedChange = { isStirrerOn = it },
                                modifier = Modifier.testTag("stirrer_switch")
                            )
                        }
                    }
                }
            }

            // Chemical Substances Shelf (Click to Add into Beaker)
            Text(
                text = "Chemical Reagents Shelf",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(availableSubstances) { substance ->
                    Surface(
                        onClick = {
                            if (!addedSubstances.any { it.id == substance.id }) {
                                addedSubstances.add(substance)

                                // Check for potential safety flags
                                val ids = addedSubstances.map { it.id }
                                if (ids.contains("H2SO4") && ids.contains("NaOH")) {
                                    temperature += 15f
                                }
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.testTag("substance_${substance.id}")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(substance.baseColor.copy(alpha = 0.9f))
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = substance.formula,
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                                Text(
                                    text = substance.type,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF94A3B8),
                                    fontSize = 9.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.Default.Add, contentDescription = "Add", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            // Current Substances in Beaker & Lab Actions
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Contents in Vessel (${addedSubstances.size})",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFE2E8F0)
                        )

                        TextButton(
                            onClick = {
                                addedSubstances.clear()
                                activeReaction = null
                                temperature = 22f
                                isBurnerOn = false
                                isStirrerOn = false
                            },
                            modifier = Modifier.testTag("clear_lab_button")
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Clear", tint = Color(0xFFEF4444), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Clear Vessel", color = Color(0xFFEF4444))
                        }
                    }

                    if (addedSubstances.isEmpty()) {
                        Text(
                            text = "No reagents added yet. Tap items from the shelf above.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF64748B)
                        )
                    } else {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(addedSubstances) { s ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF1E293B)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(s.formula, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF38BDF8))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Remove",
                                            tint = Color(0xFF94A3B8),
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clickable { addedSubstances.remove(s) }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // RUN REACTION SIMULATION BUTTON
                        Button(
                            onClick = {
                                reactionRunCount++
                                val ids = addedSubstances.map { it.id }
                                val found = if (ids.size >= 2) {
                                    ReactionData.findReaction(ids[0], ids[1])
                                } else null

                                if (found != null) {
                                    activeReaction = found
                                    if (found.energyChange.contains("Exothermic", true)) {
                                        temperature += 18f
                                    }
                                } else {
                                    activeReaction = null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("run_lab_reaction_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = LocalizationManager.getString("run_reaction"),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            // Reaction Outcome / Analysis Card
            if (activeReaction != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reaction_outcome_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(Color(0xFF06B6D4), Color(0xFF8B5CF6))))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Science, contentDescription = null, tint = Color(0xFF06B6D4))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Reaction Detected: ${activeReaction?.name}",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Balanced Chemical Equation
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF1E293B),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = activeReaction?.equation ?: "",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Classification: ${activeReaction?.reactionType}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFF59E0B))
                        Text("Observations: ${activeReaction?.observableChanges}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFCBD5E1))
                        Text("Enthalpy / Energy: ${activeReaction?.energyChange}", style = MaterialTheme.typography.bodySmall, color = Color(0xFF10B981))

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { activeReaction?.let { onViewReactionDetail(it.id) } },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B), contentColor = Color(0xFF38BDF8))
                        ) {
                            Text("Open Full Molecular Animation & Mechanism", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else if (addedSubstances.size >= 2 && reactionRunCount > 0) {
                // No reaction modeled
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Text(
                        text = "No significant chemical reaction observed under current conditions. Substances remain mixed in solution.",
                        modifier = Modifier.padding(14.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Safety Warning Alert Dialog
        if (showSafetyWarning != null) {
            AlertDialog(
                onDismissRequest = { showSafetyWarning = null },
                icon = { Icon(Icons.Default.Warning, contentDescription = "Safety Alert", tint = Color(0xFFEF4444)) },
                title = { Text("Laboratory Safety Alert") },
                text = { Text(showSafetyWarning ?: "") },
                confirmButton = {
                    TextButton(onClick = { showSafetyWarning = null }) {
                        Text("Acknowledge & Proceed")
                    }
                }
            )
        }
    }
}
