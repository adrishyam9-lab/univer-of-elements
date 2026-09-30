package com.example.chemlabx.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.chemlabx.ui.navigation.Screen

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    val popularExperiments = listOf(
        Pair("exp_acid_base_titration", "Acid-Base Neutralization Titration"),
        Pair("exp_golden_rain", "Golden Rain: Lead Iodide Precipitation"),
        Pair("exp_metal_displacement", "Iron in Copper Sulfate Displacement"),
        Pair("exp_catalytic_h2o2", "Catalytic Decomposition of H₂O₂")
    )

    val recentlyExplored = listOf(
        Triple("Sodium (Na)", "Element #11 • Alkali Metal", Screen.ElementDetail.createRoute(11)),
        Triple("Water (H₂O)", "Compound • Bent Polar", Screen.MoleculeDetail.createRoute("water")),
        Triple("Golden Rain", "Reaction • PbI₂ Precipitate", Screen.ReactionSimulator.route),
        Triple("Gold (Au)", "Element #79 • Transition Metal", Screen.ElementDetail.createRoute(79))
    )

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("app_title"),
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Futuristic Hero Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_hero_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(Color(0xFF06B6D4), Color(0xFF8B5CF6)))
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF06B6D4).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Science,
                                contentDescription = null,
                                tint = Color(0xFF06B6D4),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = LocalizationManager.getString("app_title"),
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                                color = Color(0xFFF8FAFC)
                            )
                            Text(
                                text = "Virtual Chemistry Laboratory",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "“" + LocalizationManager.getString("app_subtitle") + "”",
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                        color = Color(0xFF94A3B8),
                        lineHeight = 20.sp
                    )
                }
            }

            // Main Laboratory Features Grid (Cards from User Brief)
            Text(
                text = "Laboratory Suites",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            // Organic Chemistry Dashboard Featured Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(Screen.OrganicChemistry.route) }
                    .testTag("main_card_organic_chemistry"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF062A1F)),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(Color(0xFF10B981), Color(0xFF06B6D4)))
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF10B981).copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Hub,
                            contentDescription = null,
                            tint = Color(0xFF34D399),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Organic Chemistry Hub",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF10B981).copy(alpha = 0.2f)
                            ) {
                                Text(
                                    text = "20 GROUPS",
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF34D399)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "All functional groups, IUPAC naming rules, seniorities, named reactions & lab tests",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFA7F3D0),
                            lineHeight = 16.sp
                        )
                    }
                    Text(
                        text = "Open →",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF34D399),
                        fontSize = 13.sp
                    )
                }
            }

            // Grid Row 1: Virtual Lab & Periodic Table
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MainFeatureCard(
                    title = "Virtual Lab",
                    subtitle = "Beakers, burners, stirrers & real substances sandbox",
                    icon = Icons.Default.Science,
                    color = Color(0xFF06B6D4),
                    tag = "main_card_virtual_lab",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.VirtualLab.route) }
                )
                MainFeatureCard(
                    title = "Periodic Table",
                    subtitle = "118 elements with 2D/3D Bohr atom animations",
                    icon = Icons.Default.GridOn,
                    color = Color(0xFF8B5CF6),
                    tag = "main_card_periodic_table",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.PeriodicTable.route) }
                )
            }

            // Grid Row 2: Molecule Explorer & Reaction Simulator
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MainFeatureCard(
                    title = "Molecule Explorer",
                    subtitle = "Ball & stick, space-filling, 3D & bond visualizer",
                    icon = Icons.Default.Stream,
                    color = Color(0xFF3B82F6),
                    tag = "main_card_molecules",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.MoleculeExplorer.route) }
                )
                MainFeatureCard(
                    title = "Reaction Simulator",
                    subtitle = "Step-by-step molecular animations & WHY it happened",
                    icon = Icons.Default.Hub,
                    color = Color(0xFFF59E0B),
                    tag = "main_card_reactions",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.ReactionSimulator.route) }
                )
            }

            // Grid Row 3: Experiments & Quiz
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MainFeatureCard(
                    title = "Experiments",
                    subtitle = "Curriculum guided lab experiments & titration",
                    icon = Icons.Default.ViewInAr,
                    color = Color(0xFF10B981),
                    tag = "main_card_experiments",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.ExperimentLibrary.route) }
                )
                MainFeatureCard(
                    title = "Quiz & Mystery",
                    subtitle = "Adaptive questions & Lab Mystery challenges",
                    icon = Icons.Default.Psychology,
                    color = Color(0xFFEC4899),
                    tag = "main_card_quiz",
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Screen.Quiz.route) }
                )
            }

            // ChemTutor AI Full-Width Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(Screen.ChemTutor.route) }
                    .testTag("main_card_chemtutor"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B)),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Color(0xFF8B5CF6), Color(0xFF38BDF8))))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8B5CF6).copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color(0xFFA78BFA))
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("ChemTutor AI", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            if (com.example.chemlabx.data.SubscriptionManager.isProSubscribed) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF10B981).copy(alpha = 0.2f)
                                ) {
                                    Text("PRO", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
                                }
                            } else {
                                val rem = com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions()
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF38BDF8).copy(alpha = 0.2f)
                                ) {
                                    Text("$rem/3 Free", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
                                }
                            }
                        }
                        Text("Ask questions, explain reactions & bond mechanisms at your level", style = MaterialTheme.typography.bodySmall, color = Color(0xFFC7D2FE))
                    }
                    Text("Chat →", fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8), fontSize = 13.sp)
                }
            }

            // Continue Learning / Recently Explored
            Column {
                Text(
                    text = "Recently Explored",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFFF8FAFC)
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(recentlyExplored) { (title, subtitle, route) ->
                        Surface(
                            onClick = { onNavigate(route) },
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF0F172A),
                            modifier = Modifier.width(160.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF38BDF8))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8), fontSize = 11.sp)
                            }
                        }
                    }
                }
            }

            // Popular Experiments Section
            Column {
                Text(
                    text = "Popular Guided Experiments",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFFF8FAFC)
                )
                Spacer(modifier = Modifier.height(10.dp))
                popularExperiments.forEach { (expId, title) ->
                    Surface(
                        onClick = { onNavigate(Screen.ExperimentDetail.createRoute(expId)) },
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF0F172A),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(title, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium), color = Color(0xFFE2E8F0), modifier = Modifier.weight(1f))
                            Text("Start →", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MainFeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    tag: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .testTag(tag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFF8FAFC)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8),
                lineHeight = 15.sp,
                fontSize = 11.sp
            )
        }
    }
}
