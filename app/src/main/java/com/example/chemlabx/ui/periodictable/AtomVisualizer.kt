package com.example.chemlabx.ui.periodictable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.model.Element
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AtomVisualizer(
    element: Element,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(true) }
    var isSlowMotion by remember { mutableStateOf(false) }
    var scale by remember { mutableFloatStateOf(1.0f) }
    var userRotationAngle by remember { mutableFloatStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "atom_rotation")
    val baseDuration = if (isSlowMotion) 16000 else 6000
    val continuousAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = baseDuration, easing = LinearEasing)
        ),
        label = "continuous_orbit"
    )

    val currentOrbitAngle = if (isPlaying) continuousAngle else 0f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("atom_visualizer_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0F172A)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Model Name & Disclaimer Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${element.name} (${element.symbol}) — Atomic Bohr Model",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Text(
                        text = "Config: ${element.electronConfiguration} • Valence: ${element.valenceElectrons} e⁻",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B)
                ) {
                    Text(
                        text = "Z = ${element.number}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Canvas for 2D/3D Interactive Bohr Atom Orbitals
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFF1E293B), Color(0xFF090D16))
                        )
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(0.6f, 2.2f)
                            userRotationAngle += pan.x * 0.5f
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val baseRadius = (minOf(size.width, size.height) / 2.6f) * scale
                    val shellCount = element.shells.size
                    val stepRadius = if (shellCount > 0) baseRadius / (shellCount + 0.8f) else baseRadius

                    // 1. Draw Concentric Shell Orbits (K, L, M, N, O, P, Q)
                    val shellLetters = listOf("K", "L", "M", "N", "O", "P", "Q")
                    element.shells.forEachIndexed { index, electronCount ->
                        val orbitRadius = stepRadius * (index + 1)
                        val isValenceShell = index == element.shells.lastIndex

                        // Orbit ring
                        drawCircle(
                            color = if (isValenceShell) Color(0xFF06B6D4).copy(alpha = 0.6f) else Color(0xFF64748B).copy(alpha = 0.35f),
                            radius = orbitRadius,
                            center = center,
                            style = Stroke(width = if (isValenceShell) 2.dp.toPx() else 1.2.dp.toPx())
                        )

                        // 2. Draw Orbiting Electrons on this Shell
                        val speedMultiplier = (index + 1) * 0.7f
                        val direction = if (index % 2 == 0) 1f else -1f
                        val shellRotation = (currentOrbitAngle * speedMultiplier * direction + userRotationAngle) % 360f

                        val angleStep = 360.0 / electronCount.coerceAtLeast(1)
                        for (e in 0 until electronCount) {
                            val theta = Math.toRadians(shellRotation + (e * angleStep))
                            val electronX = center.x + (orbitRadius * cos(theta)).toFloat()
                            val electronY = center.y + (orbitRadius * sin(theta) * 0.85f).toFloat() // Slight 3D tilted ellipse perspective

                            // Electron glow
                            drawCircle(
                                color = if (isValenceShell) Color(0xFF38BDF8).copy(alpha = 0.5f) else Color(0xFF818CF8).copy(alpha = 0.4f),
                                radius = 6.dp.toPx(),
                                center = Offset(electronX, electronY)
                            )
                            // Electron core
                            drawCircle(
                                color = if (isValenceShell) Color(0xFFE0F2FE) else Color(0xFFC7D2FE),
                                radius = 3.5.dp.toPx(),
                                center = Offset(electronX, electronY)
                            )
                        }
                    }

                    // 3. Draw Central Nucleus (Protons + Neutrons cluster)
                    val nucleusRadius = (16.dp.toPx() * scale).coerceIn(12.dp.toPx(), 26.dp.toPx())
                    // Nucleus outer glow
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFEF4444), Color(0xFFF59E0B), Color.Transparent),
                            center = center,
                            radius = nucleusRadius * 1.8f
                        ),
                        radius = nucleusRadius * 1.8f,
                        center = center
                    )
                    // Nucleus dense core
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFFA000), Color(0xFFE11D48)),
                            center = center,
                            radius = nucleusRadius
                        ),
                        radius = nucleusRadius,
                        center = center
                    )
                }

                // Interactive Overlay Hint
                Text(
                    text = "Drag to rotate • Pinch to zoom",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64748B),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Playback Controls: Play/Pause, Slow-Mo, Zoom Slider, Reset
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledIconButton(
                        onClick = { isPlaying = !isPlaying },
                        shape = CircleShape,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.testTag("atom_play_pause_button")
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    FilledIconButton(
                        onClick = { isSlowMotion = !isSlowMotion },
                        shape = CircleShape,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = if (isSlowMotion) Color(0xFFF59E0B) else Color(0xFF1E293B)
                        ),
                        modifier = Modifier.testTag("atom_slow_motion_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.SlowMotionVideo,
                            contentDescription = "Slow Motion",
                            tint = if (isSlowMotion) Color.Black else Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    FilledIconButton(
                        onClick = {
                            scale = 1.0f
                            userRotationAngle = 0f
                        },
                        shape = CircleShape,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFF1E293B)
                        ),
                        modifier = Modifier.testTag("atom_reset_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.RestartAlt,
                            contentDescription = "Reset View",
                            tint = Color.White
                        )
                    }
                }

                // Zoom Slider
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(140.dp)
                ) {
                    Text(
                        text = "Zoom",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF94A3B8),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Slider(
                        value = scale,
                        onValueChange = { scale = it },
                        valueRange = 0.6f..2.0f,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mandatory Educational Model Label
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E293B).copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Educational Model Notice",
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = LocalizationManager.getString("educational_model_disclaimer"),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8),
                    lineHeight = 14.sp
                )
            }
        }
    }
}
