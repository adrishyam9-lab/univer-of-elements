package com.example.chemlabx.ui.organic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Science
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.OrganicChemistryData
import com.example.chemlabx.data.model.OrganicReaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunctionalGroupDetailScreen(
    groupId: String,
    onNavigateBack: () -> Unit,
    onNavigateToReaction: (String) -> Unit
) {
    val group = OrganicChemistryData.getGroupById(groupId)

    if (group == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Group Not Found") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Functional group not found.")
            }
        }
        return
    }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = group.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Text(
                            text = group.hindiName,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF94A3B8)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
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
            // Identity & Header Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(group.category.colorHex).copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(group.category.colorHex).copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Science,
                                    contentDescription = null,
                                    tint = Color(group.category.colorHex),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = group.category.displayName,
                                    fontSize = 11.sp,
                                    color = Color(group.category.colorHex),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = group.generalFormula,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    color = Color(0xFFF8FAFC)
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFEF4444).copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEF4444).copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = "IUPAC Priority #${group.priorityOrder}",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF87171)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = group.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFCBD5E1),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // 2D Visual Structure Box
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "2D Structure Representation",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF94A3B8)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = group.visualStructure2D,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF38BDF8)
                            )
                        }
                    }
                }
            }

            // Chemical & Physical Properties Grid
            Text(
                text = "Structure & Physical Properties",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF38BDF8)
            )

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    PropertyRow(label = "Hybridization", value = group.hybridization)
                    PropertyRow(label = "Bond Angle / Geometry", value = group.bondAngle)
                    PropertyRow(label = "Polarity & H-Bonding", value = group.polarity)
                    PropertyRow(label = "Boiling Point Trend", value = group.boilingPointTrend)
                    PropertyRow(label = "Water Solubility", value = group.solubility)
                    PropertyRow(label = "Acid-Base Character", value = group.acidityBasicity)
                }
            }

            // IUPAC Nomenclature Rules
            Text(
                text = "IUPAC Nomenclature & Naming Rules",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF34D399)
            )

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF1E293B),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("IUPAC Suffix", fontSize = 10.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                                Text(group.iupacSuffix, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
                            }
                        }
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF1E293B),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("IUPAC Prefix", fontSize = 10.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
                                Text(group.iupacPrefix, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFBBF24))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    group.iupacNamingRules.forEach { rule ->
                        Text(
                            text = "• $rule",
                            fontSize = 12.sp,
                            color = Color(0xFFCBD5E1),
                            modifier = Modifier.padding(vertical = 2.dp),
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Concrete Examples with IUPAC & Common Names
            Text(
                text = "Key Representative Compounds & IUPAC Names",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFF8FAFC)
            )

            group.examples.forEach { example ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = example.iupacName,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFF38BDF8)
                                )
                                Text(
                                    text = "Common: ${example.commonName}",
                                    fontSize = 11.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFF1E293B)
                            ) {
                                Text(
                                    text = example.formula,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF34D399),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Structure: ${example.condensedFormula}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = Color(0xFFE2E8F0)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = example.description,
                            fontSize = 11.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }

            // Chemical Reactions Section
            Text(
                text = "Chemical Reactions of ${group.name} (${group.reactions.size})",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF8B5CF6)
            )

            group.reactions.forEach { rx ->
                ReactionSummaryCard(
                    reaction = rx,
                    onClick = { onNavigateToReaction(rx.id) }
                )
            }

            // Identification Tests Section
            if (group.identificationTests.isNotEmpty()) {
                Text(
                    text = "Laboratory Identification Tests",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFFF59E0B)
                )

                group.identificationTests.forEach { test ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = test.testName,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFFBBF24)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Reagent: ${test.reagent}",
                                fontSize = 11.sp,
                                color = Color(0xFFE2E8F0)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFF10B981).copy(alpha = 0.15f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Observation: ${test.observation}",
                                    fontSize = 11.sp,
                                    color = Color(0xFF34D399),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = test.chemicalEquation,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color(0xFF38BDF8)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PropertyRow(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
        Text(text = value, fontSize = 12.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
    }
}

@Composable
private fun ReactionSummaryCard(
    reaction: OrganicReaction,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("detail_reaction_${reaction.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reaction.name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF38BDF8),
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(reaction.reactionType.badgeColorHex).copy(alpha = 0.2f)
                ) {
                    Text(
                        text = reaction.reactionType.displayName,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(reaction.reactionType.badgeColorHex)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFF1E293B),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = reaction.equation,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = Color(0xFF34D399),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mechanism: ${reaction.mechanismType}",
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Full Details →",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF38BDF8)
                )
            }
        }
    }
}
