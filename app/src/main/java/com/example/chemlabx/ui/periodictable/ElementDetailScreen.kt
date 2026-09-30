package com.example.chemlabx.ui.periodictable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.ElementData
import com.example.chemlabx.data.model.Element

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementDetailScreen(
    atomicNumber: Int,
    onBackClick: () -> Unit,
    onOpenInLabClick: (String) -> Unit
) {
    val element: Element = remember(atomicNumber) {
        ElementData.allElements.firstOrNull { it.number == atomicNumber }
            ?: ElementData.allElements.first()
    }

    val catColor = Color(element.category.colorHex)
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${element.name} (${element.symbol})") },
                navigationIcon = {
                    IconButton(onClick = onBackClick, modifier = Modifier.testTag("element_back_button")) {
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
            // Hero Element Identity Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(catColor.copy(alpha = 0.25f))
                            .border(2.dp, catColor, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${element.number}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF94A3B8)
                            )
                            Text(
                                text = element.symbol,
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = element.name,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = catColor.copy(alpha = 0.2f),
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = element.category.displayName,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = catColor
                            )
                        }
                        Text(
                            text = "Standard Atomic Weight: ${element.atomicMass} u",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }

            // Interactive Atomic Visualization (Bohr & Orbital Model Canvas)
            AtomVisualizer(element = element)

            // Button to open element in Virtual Lab Sandbox
            Button(
                onClick = { onOpenInLabClick(element.symbol) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("element_open_in_lab_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Black
                )
            ) {
                Icon(Icons.Default.Science, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Experiment with ${element.name} in Virtual Lab", fontWeight = FontWeight.Bold)
            }

            // Chemical Properties Grid Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Electronic & Chemical Properties",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    PropertyRow("Electron Configuration", element.electronConfiguration)
                    PropertyRow("Shell Distribution", element.shells.joinToString(", ") { "$it e⁻" })
                    PropertyRow("Valence Electrons", "${element.valenceElectrons}")
                    PropertyRow("Common Oxidation States", element.oxidationStates)
                    PropertyRow("Common Ions", element.commonIons)
                    PropertyRow("Electronegativity (Pauling)", element.electronegativity?.toString() ?: "N/A")
                    PropertyRow("Atomic Radius", element.atomicRadiusPm?.let { "$it pm" } ?: "N/A")
                    PropertyRow("1st Ionization Energy", element.ionizationEnergyKjMol?.let { "$it kJ/mol" } ?: "N/A")
                    PropertyRow("Electron Affinity", element.electronAffinityKjMol?.let { "$it kJ/mol" } ?: "N/A")
                }
            }

            // Physical & Thermodynamic Properties
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Physical & Thermodynamic Data",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF59E0B)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    PropertyRow("Phase at STP", element.phase.label)
                    PropertyRow("Melting Point", element.meltingPointC?.let { "$it °C (${(it + 273.15).toInt()} K)" } ?: "N/A")
                    PropertyRow("Boiling Point", element.boilingPointC?.let { "$it °C (${(it + 273.15).toInt()} K)" } ?: "N/A")
                    PropertyRow("Density (at STP)", element.densityGcm3?.let { "$it g/cm³" } ?: "N/A")
                    PropertyRow("Period / Group / Block", "Period ${element.period}, Group ${element.group ?: "f-block"}, Block '${element.block}'")
                    PropertyRow("Natural vs Synthetic", if (element.isSynthetic) "Synthetic (Particle Accelerator / Reactor)" else "Naturally Occurring")
                }
            }

            // Discovery Information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Discovery & History",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF8B5CF6)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PropertyRow("Discoverer", element.discoveredBy)
                    PropertyRow("Year Discovered", element.yearDiscovered)
                }
            }

            // Real-World Applications
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Practical Applications",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF10B981)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    element.applications.forEach { app ->
                        Row(modifier = Modifier.padding(vertical = 3.dp), verticalAlignment = Alignment.Top) {
                            Text("• ", color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                            Text(app, style = MaterialTheme.typography.bodyMedium, color = Color(0xFFE2E8F0))
                        }
                    }
                }
            }

            // Safety Information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF241416))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Warning, contentDescription = "Safety Warning", tint = Color(0xFFEF4444))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Safety & Hazard Information",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFEF4444)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = element.safety,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFFECACA),
                        lineHeight = 20.sp
                    )
                }
            }

            // Interesting Science Fact
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0B2135))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "💡 Fascinating Fact",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = element.interestingFact,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE0F2FE),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PropertyRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF94A3B8)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFFF1F5F9),
            textAlign = androidx.compose.ui.text.style.TextAlign.End,
            modifier = Modifier.width(190.dp)
        )
    }
}
