package com.example.chemlabx.ui.molecules

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.CompoundData
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.model.Compound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoleculeDetailScreen(
    compoundId: String,
    onBackClick: () -> Unit
) {
    val compound: Compound = remember(compoundId) {
        CompoundData.compounds.firstOrNull { it.id == compoundId }
            ?: CompoundData.compounds.first()
    }

    var viewMode by remember { mutableStateOf(MoleculeViewMode.BALL_AND_STICK) }
    var showBondVisualizer by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${compound.name} (${compound.formula})") },
                navigationIcon = {
                    IconButton(onClick = onBackClick, modifier = Modifier.testTag("molecule_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
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
            // Hero Title Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = compound.name,
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Text(
                            text = "IUPAC: ${compound.iupacName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF94A3B8)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B)
                    ) {
                        Text(
                            text = compound.formula,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF38BDF8)
                        )
                    }
                }
            }

            // Visualization Mode Selector Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(MoleculeViewMode.entries) { mode ->
                    FilterChip(
                        selected = viewMode == mode,
                        onClick = { viewMode = mode },
                        label = { Text(mode.title, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.testTag("mode_${mode.name}")
                    )
                }
            }

            // Interactive 3D/2D Molecular Canvas
            Molecule3DCanvas(
                compound = compound,
                viewMode = viewMode,
                modifier = Modifier.height(300.dp)
            )

            // Major Feature: "VIEW BOND STRUCTURE" Button
            Button(
                onClick = { showBondVisualizer = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("view_bond_structure_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Hub, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = LocalizationManager.getString("view_bond_structure"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            // Molecular Geometry & Physics Parameters Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Structural & Geometry Parameters",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    DetailPropertyRow("Molecular Formula", compound.formula)
                    DetailPropertyRow("Molecular Mass", "${compound.molarMass} g/mol")
                    DetailPropertyRow("Molecular Geometry", compound.geometry)
                    DetailPropertyRow("Bond Angles", compound.bondAngles)
                    DetailPropertyRow("Polarity", compound.polarity)
                    DetailPropertyRow("Atoms Count", "${compound.atoms.size} atoms")
                    DetailPropertyRow("Bonds Count", "${compound.bonds.size} bonds")
                    DetailPropertyRow("Functional Groups", compound.functionalGroups.joinToString(", "))
                }
            }

            // Lewis Structure & Bonding Explanation Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Lewis Structure & Valence",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF10B981)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = compound.lewisNotes,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE2E8F0),
                        lineHeight = 20.sp
                    )
                }
            }

            // Description Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Chemical Overview",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF59E0B)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = compound.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE2E8F0),
                        lineHeight = 20.sp
                    )
                }
            }

            // Safety Card
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
                            text = "Safety Information",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFEF4444)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = compound.safetyInfo,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFFECACA),
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Show Bond Visualizer Dialog
        if (showBondVisualizer) {
            BondVisualizerDialog(
                compound = compound,
                onDismiss = { showBondVisualizer = false }
            )
        }
    }
}

@Composable
private fun DetailPropertyRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFFF1F5F9),
            textAlign = androidx.compose.ui.text.style.TextAlign.End,
            modifier = Modifier.width(180.dp)
        )
    }
}
