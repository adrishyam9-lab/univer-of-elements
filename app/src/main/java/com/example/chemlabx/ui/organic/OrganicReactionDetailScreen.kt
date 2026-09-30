package com.example.chemlabx.ui.organic

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Visibility
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganicReactionDetailScreen(
    reactionId: String,
    onNavigateBack: () -> Unit,
    onNavigateToGroup: (String) -> Unit
) {
    val reactionPair = OrganicChemistryData.findReactionById(reactionId)

    if (reactionPair == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Reaction Not Found") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Organic reaction not found.")
            }
        }
        return
    }

    val (group, reaction) = reactionPair
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = reaction.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Text(
                            text = "${group.name} • ${reaction.reactionType.displayName}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(reaction.reactionType.badgeColorHex)
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
            // Chemical Equation Hero Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(reaction.reactionType.badgeColorHex).copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth().testTag("reaction_detail_hero")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Chemical Equation",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF94A3B8)
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

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = reaction.equation,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF34D399),
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Reactants:", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                            Text(reaction.reactants, fontSize = 12.sp, color = Color(0xFFE2E8F0))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Products:", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                            Text(reaction.products, fontSize = 12.sp, color = Color(0xFFE2E8F0))
                        }
                    }
                }
            }

            // Reagents, Catalysts & Conditions
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Reaction Parameters",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )

                    Row {
                        Text("Reagents & Catalyst: ", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                        Text(reaction.reagentsAndCatalyst, fontSize = 12.sp, color = Color(0xFFF1F5F9))
                    }

                    Row {
                        Text("Conditions: ", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                        Text(reaction.conditions, fontSize = 12.sp, color = Color(0xFFF1F5F9))
                    }

                    Row {
                        Text("Mechanism Type: ", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                        Text(reaction.mechanismType, fontSize = 12.sp, color = Color(0xFFFBBF24), fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // Step-by-Step Reaction Mechanism Walkthrough
            Text(
                text = "Step-by-Step Reaction Mechanism",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF34D399)
            )

            reaction.mechanismSteps.forEachIndexed { index, step ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF10B981).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF34D399)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Step ${index + 1}",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF34D399)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFE2E8F0),
                                lineHeight = 19.sp
                            )
                        }
                    }
                }
            }

            // Key Observations
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Visibility, contentDescription = null, tint = Color(0xFFFBBF24), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Laboratory Observations",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFFBBF24)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = reaction.keyObservation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFFEF08A),
                        lineHeight = 18.sp
                    )
                }
            }

            // Synthetic Importance & Applications
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3B82F6).copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color(0xFF60A5FA), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Synthetic & Industrial Significance",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF60A5FA)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = reaction.importance,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFBFDBFE),
                        lineHeight = 18.sp
                    )
                }
            }

            // Button to return to parent functional group
            Button(
                onClick = { onNavigateToGroup(group.id) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("View All Reactions of ${group.name} (${group.generalFormula})", color = Color(0xFF38BDF8), fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
