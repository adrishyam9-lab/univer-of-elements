package com.example.chemlabx.ui.molecules

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.chemlabx.data.model.Compound
import kotlin.math.sin

@Composable
fun BondVisualizerDialog(
    compound: Compound,
    onDismiss: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Animation for electron sharing/transfer across bonds
    val infiniteTransition = rememberInfiniteTransition(label = "bond_anim")
    val electronProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing)
        ),
        label = "electron_progress"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 24.dp)
                .testTag("bond_visualizer_dialog"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(scrollState)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Chemical Bond Architecture",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF38BDF8)
                        )
                        Text(
                            text = "${compound.name} (${compound.formula})",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF94A3B8)
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("bond_dialog_close")) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Interactive Animated Bond Formation Canvas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF1E293B), Color(0xFF0B1329))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val centerY = size.height / 2f
                        val leftX = size.width * 0.28f
                        val rightX = size.width * 0.72f

                        val isIonic = compound.category.contains("Ionic", ignoreCase = true) ||
                                compound.bonds.any { it.bondType == com.example.chemlabx.data.model.BondType.IONIC }

                        // Draw Two Bonding Atoms
                        val atom1 = compound.atoms.getOrNull(0)
                        val atom2 = compound.atoms.getOrNull(1)

                        val col1 = Color(atom1?.colorHex ?: 0xFFEF4444)
                        val col2 = Color(atom2?.colorHex ?: 0xFF3B82F6)

                        // Atom 1
                        drawCircle(col1.copy(alpha = 0.3f), radius = 34.dp.toPx(), center = Offset(leftX, centerY))
                        drawCircle(col1, radius = 22.dp.toPx(), center = Offset(leftX, centerY))

                        // Atom 2
                        drawCircle(col2.copy(alpha = 0.3f), radius = 34.dp.toPx(), center = Offset(rightX, centerY))
                        drawCircle(col2, radius = 22.dp.toPx(), center = Offset(rightX, centerY))

                        if (isIonic) {
                            // IONIC BOND: Complete electron transfer path with arrow
                            val electronCurrentX = leftX + (rightX - leftX) * electronProgress
                            val arcOffset = (sin(electronProgress * Math.PI) * 35.dp.toPx()).toFloat()

                            // Electrostatic attraction waves
                            drawCircle(
                                color = Color(0xFFF59E0B).copy(alpha = 0.4f),
                                radius = 45.dp.toPx(),
                                center = Offset((leftX + rightX) / 2f, centerY),
                                style = Stroke(width = 2.dp.toPx())
                            )

                            // Traveling transferred valence electron
                            drawCircle(
                                color = Color(0xFFFBBF24),
                                radius = 7.dp.toPx(),
                                center = Offset(electronCurrentX, centerY - arcOffset)
                            )
                        } else {
                            // COVALENT BOND: Shared molecular orbital overlapping lens
                            val midX = (leftX + rightX) / 2f

                            // Overlapping electron cloud
                            drawCircle(
                                color = Color(0xFF06B6D4).copy(alpha = 0.25f),
                                radius = 36.dp.toPx(),
                                center = Offset(midX, centerY)
                            )

                            // 2 circulating shared electrons
                            val orbitRadius = 18.dp.toPx()
                            val angle1 = electronProgress * 2 * Math.PI
                            val angle2 = angle1 + Math.PI

                            val e1X = midX + (orbitRadius * kotlin.math.cos(angle1)).toFloat()
                            val e1Y = centerY + (orbitRadius * kotlin.math.sin(angle1)).toFloat()

                            val e2X = midX + (orbitRadius * kotlin.math.cos(angle2)).toFloat()
                            val e2Y = centerY + (orbitRadius * kotlin.math.sin(angle2)).toFloat()

                            drawCircle(Color(0xFFE0F2FE), radius = 5.dp.toPx(), center = Offset(e1X, e1Y))
                            drawCircle(Color(0xFFE0F2FE), radius = 5.dp.toPx(), center = Offset(e2X, e2Y))
                        }
                    }

                    Text(
                        text = if (compound.category.contains("Ionic", true)) "Ionic Electron Transfer: Na⁺ [:Cl:]⁻" else "Shared Covalent Electron Pair",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF1F5F9),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Bond Type Badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("Primary Bond Type", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                            Text(
                                text = compound.bonds.firstOrNull()?.bondType?.label ?: "Covalent",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("Molecular Polarity", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                            Text(
                                text = compound.polarity,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF59E0B)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Detailed Scientific Bond Mechanism Explanation
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "How Atoms Form This Bond",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF10B981)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = compound.bondExplanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFE2E8F0),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Lewis Structure notes
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Lewis Dot & Octet Structure",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFA78BFA)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = compound.lewisNotes,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFE2E8F0),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text("Close Bond Viewer", color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
