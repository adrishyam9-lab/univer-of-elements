package com.example.chemlabx.ui.organic

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.OrganicChemistryData
import com.example.chemlabx.data.model.OrganicCategory
import com.example.chemlabx.data.model.OrganicFunctionalGroup
import com.example.chemlabx.ui.components.ChemLabTopBar
import com.example.chemlabx.ui.navigation.Screen

@Composable
fun OrganicChemistryDashboardScreen(
    onNavigateBack: () -> Unit,
    onNavigateToGroup: (String) -> Unit,
    onNavigateToReaction: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<OrganicCategory?>(null) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = remember { listOf("Functional Groups", "IUPAC Priority & Rules", "All Reactions", "Lab Tests") }

    val filteredGroups by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            val baseList = if (searchQuery.isBlank()) {
                OrganicChemistryData.allFunctionalGroups
            } else {
                OrganicChemistryData.search(searchQuery)
            }
            if (selectedCategory == null) {
                baseList
            } else {
                baseList.filter { it.category == selectedCategory }
            }
        }
    }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = "Organic Chemistry",
                onSearchClick = onSearchClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Hero Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("organic_hero_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(Color(0xFF10B981), Color(0xFF06B6D4), Color(0xFF8B5CF6)))
                )
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
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF10B981).copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Hub,
                                    contentDescription = null,
                                    tint = Color(0xFF34D399),
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Organic Chemistry Hub",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFFF8FAFC)
                                )
                                Text(
                                    text = "कार्बनिक रसायन • IUPAC Naming & Reactions",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF34D399)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Complete interactive directory of 20 functional groups, IUPAC priority seniority order, 3D structures, named reactions, and diagnostic tests.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8),
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Stats Pills
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatBadge(label = "20 Groups", color = Color(0xFF10B981))
                        StatBadge(label = "IUPAC Seniority #1-#19", color = Color(0xFF3B82F6))
                        StatBadge(label = "Named Reactions", color = Color(0xFF8B5CF6))
                        StatBadge(label = "Lab Tests", color = Color(0xFFF59E0B))
                    }
                }
            }

            // Tab Navigation Row
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                edgePadding = 16.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        modifier = Modifier.testTag("organic_tab_$index")
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    // TAB 0: Functional Groups Explorer
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Search bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Search functional group, IUPAC name, formula...", fontSize = 12.sp) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF94A3B8)) },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color(0xFF94A3B8))
                                    }
                                }
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .testTag("organic_search_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF10B981),
                                unfocusedBorderColor = Color(0xFF334155),
                                focusedContainerColor = Color(0xFF0F172A),
                                unfocusedContainerColor = Color(0xFF0F172A)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )

                        // Category filter chips
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            item {
                                FilterChip(
                                    selected = selectedCategory == null,
                                    onClick = { selectedCategory = null },
                                    label = { Text("All (${OrganicChemistryData.allFunctionalGroups.size})", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(0xFF10B981).copy(alpha = 0.2f),
                                        selectedLabelColor = Color(0xFF34D399)
                                    )
                                )
                            }
                            items(OrganicCategory.values()) { cat ->
                                val count = OrganicChemistryData.allFunctionalGroups.count { it.category == cat }
                                FilterChip(
                                    selected = selectedCategory == cat,
                                    onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
                                    label = { Text("${cat.displayName} ($count)", fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(cat.colorHex).copy(alpha = 0.25f),
                                        selectedLabelColor = Color(cat.colorHex)
                                    )
                                )
                            }
                        }

                        // List of Functional Groups
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(filteredGroups) { group ->
                                FunctionalGroupCard(
                                    group = group,
                                    onClick = { onNavigateToGroup(group.id) }
                                )
                            }
                        }
                    }
                }

                1 -> {
                    // TAB 1: IUPAC Priority & Golden Rules
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = "IUPAC Functional Group Priority Order (Seniority Table)",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                            Text(
                                text = "When naming polyfunctional compounds, the highest group on this list takes precedence and determines the suffix. All others become prefixes.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }

                        items(OrganicChemistryData.iupacPriorityList) { (rank, desc) ->
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF0F172A),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (rank <= 3) Color(0xFFEF4444).copy(alpha = 0.2f)
                                                else if (rank <= 9) Color(0xFFF59E0B).copy(alpha = 0.2f)
                                                else Color(0xFF3B82F6).copy(alpha = 0.2f)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "#$rank",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            color = if (rank <= 3) Color(0xFFF87171) else if (rank <= 9) Color(0xFFFBBF24) else Color(0xFF60A5FA)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = desc,
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                        color = Color(0xFFF1F5F9),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "5 Golden Rules of IUPAC Nomenclature",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF34D399)
                            )
                        }

                        items(OrganicChemistryData.iupacGoldenRules) { (title, rule) ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.3f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = Color(0xFF34D399)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = rule,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFFCBD5E1),
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }

                2 -> {
                    // TAB 2: All Reactions Catalog
                    val allReactions = remember { OrganicChemistryData.getAllReactions() }
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = "Organic Reactions & Mechanisms (${allReactions.size})",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )
                            Text(
                                text = "Tap any reaction to view detailed mechanisms, reagents, conditions, and driving forces.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }

                        items(allReactions) { (group, reaction) ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onNavigateToReaction(reaction.id) }
                                    .testTag("reaction_item_${reaction.id}"),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
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
                                            shape = RoundedCornerShape(8.dp),
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

                                    // Chemical Equation Box
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = Color(0xFF1E293B),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = reaction.equation,
                                            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.SemiBold),
                                            color = Color(0xFF34D399),
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Group: ${group.name}",
                                            fontSize = 11.sp,
                                            color = Color(0xFF94A3B8)
                                        )
                                        Text(
                                            text = "View Mechanism →",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF38BDF8)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                3 -> {
                    // TAB 3: Diagnostic Lab Tests
                    val allTests = remember {
                        OrganicChemistryData.allFunctionalGroups.flatMap { g ->
                            g.identificationTests.map { t -> Pair(g, t) }
                        }
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = "Qualitative Organic Analysis Lab Tests (${allTests.size})",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF59E0B)
                            )
                            Text(
                                text = "Diagnostic laboratory tests used to identify and distinguish functional groups in practical examinations.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }

                        items(allTests) { (group, test) ->
                            Card(
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.3f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = test.testName,
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                            color = Color(0xFFFBBF24)
                                        )
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = Color(0xFF3B82F6).copy(alpha = 0.2f)
                                        ) {
                                            Text(
                                                text = group.name,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF60A5FA)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Reagent: " + test.reagent,
                                        fontSize = 11.sp,
                                        color = Color(0xFFE2E8F0),
                                        fontWeight = FontWeight.Medium
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Observation pill
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color(0xFF10B981).copy(alpha = 0.15f),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.4f)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(modifier = Modifier.padding(8.dp)) {
                                            Text(
                                                text = "Observation:",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF34D399)
                                            )
                                            Text(
                                                text = test.observation,
                                                fontSize = 12.sp,
                                                color = Color(0xFFF1F5F9)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

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
                }
            }
        }
    }
}

@Composable
private fun FunctionalGroupCard(
    group: OrganicFunctionalGroup,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("group_card_${group.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = group.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0xFFEF4444).copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "Priority #${group.priorityOrder}",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF87171)
                            )
                        }
                    }
                    Text(
                        text = group.hindiName,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8),
                        fontSize = 11.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(group.category.colorHex).copy(alpha = 0.2f)
                ) {
                    Text(
                        text = group.category.displayName,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(group.category.colorHex)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // General Formula & Suffix Pill Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Formula", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                        Text(group.generalFormula, fontSize = 12.sp, fontFamily = FontFamily.Monospace, color = Color(0xFF38BDF8), fontWeight = FontWeight.SemiBold)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("IUPAC Suffix", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                        Text(group.iupacSuffix, fontSize = 12.sp, color = Color(0xFF34D399), fontWeight = FontWeight.SemiBold)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF1E293B),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("IUPAC Prefix", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                        Text(group.iupacPrefix, fontSize = 12.sp, color = Color(0xFFFBBF24), fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer with reaction count & arrow
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${group.reactions.size} Reactions • ${group.examples.size} Key Examples",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8)
                )
                Text(
                    text = "Explore Details →",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun StatBadge(label: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = color.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.3f))
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}
