package com.example.chemlabx.ui.molecules

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.model.Atom3D
import com.example.chemlabx.data.model.ChemicalBond
import com.example.chemlabx.data.model.Compound
import kotlin.math.cos
import kotlin.math.sin

enum class MoleculeViewMode(val title: String) {
    BALL_AND_STICK("Ball & Stick"),
    SPACE_FILLING("Space Filling"),
    WIREFRAME("Wireframe"),
    LEWIS_STRUCTURE("Lewis Structure"),
    STRUCTURAL_2D("2D Structural Formula")
}

data class ProjectedAtom(
    val atom: Atom3D,
    val originalIndex: Int,
    val projX: Float,
    val projY: Float,
    val depthZ: Float
)

@Composable
fun Molecule3DCanvas(
    compound: Compound,
    viewMode: MoleculeViewMode = MoleculeViewMode.BALL_AND_STICK,
    isAutoRotate: Boolean = true,
    modifier: Modifier = Modifier
) {
    var rotX by remember { mutableFloatStateOf(15f) }
    var rotY by remember { mutableFloatStateOf(25f) }
    var scale by remember { mutableFloatStateOf(1.0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "mol_spin")
    val autoSpin by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 18000, easing = LinearEasing)
        ),
        label = "auto_spin"
    )

    val currentYaw = rotY + if (isAutoRotate) autoSpin else 0f
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF1E293B), Color(0xFF090D16))
                )
            )
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.6f, 2.5f)
                    rotY += pan.x * 0.6f
                    rotX = (rotX - pan.y * 0.6f).coerceIn(-80f, 80f)
                }
            }
            .testTag("molecule_3d_canvas"),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val baseUnit = (minOf(size.width, size.height) / 4.2f) * scale

            val radX = Math.toRadians(rotX.toDouble())
            val radY = Math.toRadians(currentYaw.toDouble())

            // 1. 3D Rotation projection
            val projectedAtoms = compound.atoms.mapIndexed { index, atom ->
                // Rotate around Y-axis (Yaw)
                val x1 = (atom.x * cos(radY) + atom.z * sin(radY)).toFloat()
                val y1 = atom.y
                val z1 = (-atom.x * sin(radY) + atom.z * cos(radY)).toFloat()

                // Rotate around X-axis (Pitch)
                val x2 = x1
                val y2 = (y1 * cos(radX) - z1 * sin(radX)).toFloat()
                val z2 = (y1 * sin(radX) + z1 * cos(radX)).toFloat()

                // 2D Screen projection
                val screenX = center.x + x2 * baseUnit
                val screenY = center.y + y2 * baseUnit

                ProjectedAtom(atom, index, screenX, screenY, z2)
            }

            when (viewMode) {
                MoleculeViewMode.BALL_AND_STICK -> {
                    drawBallAndStick(compound, projectedAtoms, textMeasurer, baseUnit)
                }
                MoleculeViewMode.SPACE_FILLING -> {
                    drawSpaceFilling(projectedAtoms, baseUnit)
                }
                MoleculeViewMode.WIREFRAME -> {
                    drawWireframe(compound, projectedAtoms, textMeasurer)
                }
                MoleculeViewMode.LEWIS_STRUCTURE -> {
                    drawLewisStructure(compound, projectedAtoms, textMeasurer, center)
                }
                MoleculeViewMode.STRUCTURAL_2D -> {
                    drawStructuralFormula2D(compound, projectedAtoms, textMeasurer, center)
                }
            }
        }

        // Gesture Instructions
        Text(
            text = "Drag to rotate • Pinch to zoom",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF64748B),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}

private fun DrawScope.drawBallAndStick(
    compound: Compound,
    projectedAtoms: List<ProjectedAtom>,
    textMeasurer: TextMeasurer,
    baseUnit: Float
) {
    // 1. Draw Bonds (cylinders between atoms)
    compound.bonds.forEach { bond ->
        val p1 = projectedAtoms.getOrNull(bond.fromAtomIndex)
        val p2 = projectedAtoms.getOrNull(bond.toAtomIndex)
        if (p1 != null && p2 != null) {
            val start = Offset(p1.projX, p1.projY)
            val end = Offset(p2.projX, p2.projY)
            val bondColor = Color(0xFF94A3B8)

            when (bond.order) {
                1 -> {
                    drawLine(
                        color = bondColor,
                        start = start,
                        end = end,
                        strokeWidth = 7.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
                2 -> {
                    // Double bond: two parallel lines
                    val dx = end.x - start.x
                    val dy = end.y - start.y
                    val len = kotlin.math.sqrt(dx * dx + dy * dy).coerceAtLeast(1f)
                    val offsetDistance = 5.dp.toPx()
                    val perpX = (-dy / len) * offsetDistance
                    val perpY = (dx / len) * offsetDistance

                    drawLine(
                        color = bondColor,
                        start = Offset(start.x + perpX, start.y + perpY),
                        end = Offset(end.x + perpX, end.y + perpY),
                        strokeWidth = 4.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = bondColor,
                        start = Offset(start.x - perpX, start.y - perpY),
                        end = Offset(end.x - perpX, end.y - perpY),
                        strokeWidth = 4.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
                3 -> {
                    // Triple bond: 3 lines
                    val dx = end.x - start.x
                    val dy = end.y - start.y
                    val len = kotlin.math.sqrt(dx * dx + dy * dy).coerceAtLeast(1f)
                    val offsetDist = 6.dp.toPx()
                    val perpX = (-dy / len) * offsetDist
                    val perpY = (dx / len) * offsetDist

                    drawLine(color = bondColor, start = start, end = end, strokeWidth = 3.5.dp.toPx(), cap = StrokeCap.Round)
                    drawLine(color = bondColor, start = Offset(start.x + perpX, start.y + perpY), end = Offset(end.x + perpX, end.y + perpY), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
                    drawLine(color = bondColor, start = Offset(start.x - perpX, start.y - perpY), end = Offset(end.x - perpX, end.y - perpY), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
                }
            }
        }
    }

    // 2. Draw Atoms with Z-Depth Sorting (render furthest back first)
    val sortedAtoms = projectedAtoms.sortedBy { it.depthZ }
    sortedAtoms.forEach { pa ->
        val atomColor = Color(pa.atom.colorHex)
        val radius = 18.dp.toPx() * pa.atom.radiusRatio
        val center = Offset(pa.projX, pa.projY)

        // 3D Shading sphere effect
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White.copy(alpha = 0.8f), atomColor, atomColor.copy(alpha = 0.8f), Color.Black),
                center = Offset(center.x - radius * 0.35f, center.y - radius * 0.35f),
                radius = radius * 1.25f
            ),
            radius = radius,
            center = center
        )

        // Atom Symbol text
        val textLayoutResult = textMeasurer.measure(
            text = pa.atom.elementSymbol,
            style = TextStyle(color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        )
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(center.x - textLayoutResult.size.width / 2f, center.y - textLayoutResult.size.height / 2f)
        )
    }
}

private fun DrawScope.drawSpaceFilling(
    projectedAtoms: List<ProjectedAtom>,
    baseUnit: Float
) {
    // Space filling: large overlapping van der Waals radii
    val sortedAtoms = projectedAtoms.sortedBy { it.depthZ }
    sortedAtoms.forEach { pa ->
        val atomColor = Color(pa.atom.colorHex)
        val vdwRadius = 38.dp.toPx() * pa.atom.radiusRatio
        val center = Offset(pa.projX, pa.projY)

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White.copy(alpha = 0.7f), atomColor, Color(0xFF0F172A)),
                center = Offset(center.x - vdwRadius * 0.3f, center.y - vdwRadius * 0.3f),
                radius = vdwRadius
            ),
            radius = vdwRadius,
            center = center
        )
    }
}

private fun DrawScope.drawWireframe(
    compound: Compound,
    projectedAtoms: List<ProjectedAtom>,
    textMeasurer: TextMeasurer
) {
    // Wireframe lines
    compound.bonds.forEach { bond ->
        val p1 = projectedAtoms.getOrNull(bond.fromAtomIndex)
        val p2 = projectedAtoms.getOrNull(bond.toAtomIndex)
        if (p1 != null && p2 != null) {
            drawLine(
                color = Color(0xFF38BDF8),
                start = Offset(p1.projX, p1.projY),
                end = Offset(p2.projX, p2.projY),
                strokeWidth = 2.dp.toPx()
            )
        }
    }

    // Vertex points & symbols
    projectedAtoms.forEach { pa ->
        drawCircle(
            color = Color(pa.atom.colorHex),
            radius = 6.dp.toPx(),
            center = Offset(pa.projX, pa.projY)
        )
        val textLayoutResult = textMeasurer.measure(
            text = pa.atom.elementSymbol,
            style = TextStyle(color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        )
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(pa.projX + 8.dp.toPx(), pa.projY - 14.dp.toPx())
        )
    }
}

private fun DrawScope.drawLewisStructure(
    compound: Compound,
    projectedAtoms: List<ProjectedAtom>,
    textMeasurer: TextMeasurer,
    center: Offset
) {
    // Flattened 2D Lewis representation with electron dot pairs
    compound.bonds.forEach { bond ->
        val p1 = projectedAtoms.getOrNull(bond.fromAtomIndex)
        val p2 = projectedAtoms.getOrNull(bond.toAtomIndex)
        if (p1 != null && p2 != null) {
            val start = Offset(p1.projX, p1.projY)
            val end = Offset(p2.projX, p2.projY)

            // Lewis dash bond
            drawLine(
                color = Color(0xFFF8FAFC),
                start = start,
                end = end,
                strokeWidth = 3.dp.toPx()
            )
        }
    }

    projectedAtoms.forEach { pa ->
        // Symbol
        val textLayout = textMeasurer.measure(
            text = pa.atom.elementSymbol,
            style = TextStyle(color = Color(pa.atom.colorHex), fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        )
        val textPos = Offset(pa.projX - textLayout.size.width / 2f, pa.projY - textLayout.size.height / 2f)

        // Backdrop badge
        drawCircle(
            color = Color(0xFF0F172A),
            radius = 16.dp.toPx(),
            center = Offset(pa.projX, pa.projY)
        )
        drawCircle(
            color = Color(pa.atom.colorHex),
            radius = 16.dp.toPx(),
            center = Offset(pa.projX, pa.projY),
            style = Stroke(width = 1.5.dp.toPx())
        )
        drawText(textLayoutResult = textLayout, topLeft = textPos)

        // Draw electron lone pairs for heteroatoms (O, N, Cl)
        if (pa.atom.elementSymbol in listOf("O", "N", "Cl", "S")) {
            val dotRadius = 2.dp.toPx()
            drawCircle(Color(0xFF38BDF8), radius = dotRadius, center = Offset(pa.projX - 4.dp.toPx(), pa.projY - 22.dp.toPx()))
            drawCircle(Color(0xFF38BDF8), radius = dotRadius, center = Offset(pa.projX + 4.dp.toPx(), pa.projY - 22.dp.toPx()))
        }
    }
}

private fun DrawScope.drawStructuralFormula2D(
    compound: Compound,
    projectedAtoms: List<ProjectedAtom>,
    textMeasurer: TextMeasurer,
    center: Offset
) {
    // Textbook 2D structural diagram
    compound.bonds.forEach { bond ->
        val p1 = projectedAtoms.getOrNull(bond.fromAtomIndex)
        val p2 = projectedAtoms.getOrNull(bond.toAtomIndex)
        if (p1 != null && p2 != null) {
            drawLine(
                color = Color(0xFFCBD5E1),
                start = Offset(p1.projX, p1.projY),
                end = Offset(p2.projX, p2.projY),
                strokeWidth = if (bond.order == 2) 4.dp.toPx() else 2.5.dp.toPx()
            )
        }
    }

    projectedAtoms.forEach { pa ->
        val textLayout = textMeasurer.measure(
            text = pa.atom.elementSymbol,
            style = TextStyle(color = Color(pa.atom.colorHex), fontSize = 14.sp, fontWeight = FontWeight.Bold)
        )
        drawCircle(color = Color(0xFF0F172A), radius = 13.dp.toPx(), center = Offset(pa.projX, pa.projY))
        drawText(
            textLayoutResult = textLayout,
            topLeft = Offset(pa.projX - textLayout.size.width / 2f, pa.projY - textLayout.size.height / 2f)
        )
    }
}
