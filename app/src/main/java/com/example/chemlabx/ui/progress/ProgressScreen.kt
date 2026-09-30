package com.example.chemlabx.ui.progress

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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.ui.components.ChemLabTopBar

@Composable
fun ProgressScreen(
    onSearchClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_progress"),
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
            // Student Banner Card with Streak & Points
            Card(
                modifier = Modifier.fillMaxWidth().testTag("progress_banner_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(Color(0xFF06B6D4), Color(0xFF8B5CF6))))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Chemist Cadet Dashboard",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Current Track: Class 9–10 Chemistry",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF94A3B8)
                        )
                    }

                    // Streak Badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFEF4444).copy(alpha = 0.2f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.LocalFireDepartment, contentDescription = "Streak", tint = Color(0xFFEF4444), modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("5 Days", fontWeight = FontWeight.Bold, color = Color(0xFFEF4444), fontSize = 13.sp)
                        }
                    }
                }
            }

            // Core Metrics Grid (4 items)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricCard(
                    title = "Elements Explored",
                    count = "32 / 118",
                    icon = Icons.Default.GridOn,
                    color = Color(0xFF06B6D4),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Compounds Explored",
                    count = "12 / 15",
                    icon = Icons.Default.Stream,
                    color = Color(0xFF3B82F6),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricCard(
                    title = "Reactions Studied",
                    count = "8 Mastered",
                    icon = Icons.Default.Science,
                    color = Color(0xFFF59E0B),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Experiments Done",
                    count = "6 Labs",
                    icon = Icons.Default.CheckCircle,
                    color = Color(0xFF10B981),
                    modifier = Modifier.weight(1f)
                )
            }

            // Quiz Performance & Points Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Knowledge Mastery & Quiz Score",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Total Points Earned", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF94A3B8))
                        Text("340 pts", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color(0xFF10B981))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Accuracy Rate", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF94A3B8))
                        Text("91.6%", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color(0xFF38BDF8))
                    }
                }
            }

            // Topics Mastered vs Focus Areas
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Curricular Competencies",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    CompetencyRow("Acid-Base Neutralization & Titration", "Mastered", Color(0xFF10B981))
                    CompetencyRow("Redox & Single Displacement", "Mastered", Color(0xFF10B981))
                    CompetencyRow("Ionic vs Covalent Bonding", "Mastered", Color(0xFF10B981))
                    CompetencyRow("Periodic Electronegativity Trends", "Reviewing", Color(0xFFF59E0B))
                    CompetencyRow("Le Chatelier's Dynamic Equilibrium", "Upcoming", Color(0xFF8B5CF6))
                }
            }

            // ChemTutor PRO Membership Card
            var showSubscriptionDialog by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }

            Card(
                modifier = Modifier.fillMaxWidth().testTag("progress_subscription_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B)),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Color(0xFF8B5CF6), Color(0xFF38BDF8))))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color(0xFFFBBF24), modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ChemTutor AI Plan",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }

                        if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF10B981).copy(alpha = 0.2f)
                            ) {
                                Text(
                                    "PRO Active",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF34D399)
                                )
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF38BDF8).copy(alpha = 0.2f)
                            ) {
                                Text(
                                    "Free Tier",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF38BDF8)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) {
                        Text(
                            text = "You have full unlimited access to ChemTutor AI, step-by-step reaction mechanisms, and university-level reasoning.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFC7D2FE)
                        )
                    } else {
                        val rem = com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions()
                        Text(
                            text = "$rem of 3 free questions remaining. Upgrade to PRO for $3/month for unlimited chemistry explanations.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFC7D2FE)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        androidx.compose.material3.Button(
                            onClick = { showSubscriptionDialog = true },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) Color(0xFF334155) else Color(0xFF38BDF8),
                                contentColor = if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) "Manage Plan ($3/mo)" else "Upgrade to PRO ($3/mo)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            if (showSubscriptionDialog) {
                com.example.chemlabx.ui.chemtutor.SubscriptionDialog(
                    onDismiss = { showSubscriptionDialog = false },
                    onSubscribed = { showSubscriptionDialog = false }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    count: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(count, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color(0xFFF8FAFC))
            Text(title, style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8), fontSize = 10.sp)
        }
    }
}

@Composable
private fun CompetencyRow(name: String, status: String, statusColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, style = MaterialTheme.typography.bodySmall, color = Color(0xFFCBD5E1))
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = statusColor.copy(alpha = 0.2f)
        ) {
            Text(
                text = status,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = statusColor,
                fontSize = 10.sp
            )
        }
    }
}
