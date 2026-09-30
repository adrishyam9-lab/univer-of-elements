package com.example.chemlabx.ui.search

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.example.chemlabx.data.ElementData
import com.example.chemlabx.data.ExperimentData
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.ReactionData

sealed class SearchResultItem {
    data class ElementResult(val number: Int, val symbol: String, val name: String, val category: String) : SearchResultItem()
    data class CompoundResult(val id: String, val formula: String, val name: String, val category: String) : SearchResultItem()
    data class ReactionResult(val id: String, val name: String, val equation: String, val type: String) : SearchResultItem()
    data class ExperimentResult(val id: String, val title: String, val category: String) : SearchResultItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChemistrySearchScreen(
    onBackClick: () -> Unit,
    onElementClick: (Int) -> Unit,
    onCompoundClick: (String) -> Unit,
    onReactionClick: (String) -> Unit,
    onExperimentClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = remember { listOf("All", "Elements", "Compounds", "Reactions", "Experiments") }

    val quickSearches = remember { listOf("NaCl", "Sodium", "Acid", "Redox", "H2O", "Lead Iodide", "Ionic Bond", "Copper") }

    val results by remember(searchQuery, selectedFilter) {
        derivedStateOf {
            if (searchQuery.isBlank()) emptyList<SearchResultItem>()
            else {
                val q = searchQuery.trim().lowercase()
                val list = mutableListOf<SearchResultItem>()

                if (selectedFilter == "All" || selectedFilter == "Elements") {
                    ElementData.allElements.filter {
                        it.name.lowercase().contains(q) || it.symbol.lowercase() == q || it.number.toString() == q || it.category.displayName.lowercase().contains(q)
                    }.forEach {
                        list.add(SearchResultItem.ElementResult(it.number, it.symbol, it.name, it.category.displayName))
                    }
                }

                if (selectedFilter == "All" || selectedFilter == "Compounds") {
                    CompoundData.compounds.filter {
                        it.name.lowercase().contains(q) || it.formula.lowercase().contains(q) || it.iupacName.lowercase().contains(q) || it.category.lowercase().contains(q)
                    }.forEach {
                        list.add(SearchResultItem.CompoundResult(it.id, it.formula, it.name, it.category))
                    }
                }

                if (selectedFilter == "All" || selectedFilter == "Reactions") {
                    ReactionData.reactions.filter {
                        it.name.lowercase().contains(q) || it.equation.lowercase().contains(q) || it.reactionType.lowercase().contains(q) || it.reactants.any { r -> r.lowercase().contains(q) }
                    }.forEach {
                        list.add(SearchResultItem.ReactionResult(it.id, it.name, it.equation, it.reactionType))
                    }
                }

                if (selectedFilter == "All" || selectedFilter == "Experiments") {
                    ExperimentData.experiments.filter {
                        it.title.lowercase().contains(q) || it.category.lowercase().contains(q) || it.objective.lowercase().contains(q)
                    }.forEach {
                        list.add(SearchResultItem.ExperimentResult(it.id, it.title, it.category))
                    }
                }

                list
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Universal Chemistry Search") },
                navigationIcon = {
                    IconButton(onClick = onBackClick, modifier = Modifier.testTag("search_back_button")) {
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
        ) {
            // Search Input Field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("universal_search_input"),
                placeholder = { Text("Search NaCl, Sodium, acid, redox, benzene...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            // Filter Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Quick searches suggestions when empty
            if (searchQuery.isBlank()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Popular Searches",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF94A3B8)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(quickSearches) { tag ->
                            Surface(
                                onClick = { searchQuery = tag },
                                shape = RoundedCornerShape(14.dp),
                                color = Color(0xFF1E293B)
                            ) {
                                Text(
                                    text = tag,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF38BDF8)
                                )
                            }
                        }
                    }
                }
            }

            // Results List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(results) { item ->
                    when (item) {
                        is SearchResultItem.ElementResult -> {
                            SearchResultCard(
                                icon = Icons.Default.GridOn,
                                title = "${item.name} (${item.symbol})",
                                subtitle = "Atomic #${item.number} • ${item.category}",
                                badge = "Element",
                                badgeColor = Color(0xFF06B6D4),
                                onClick = { onElementClick(item.number) }
                            )
                        }
                        is SearchResultItem.CompoundResult -> {
                            SearchResultCard(
                                icon = Icons.Default.Stream,
                                title = "${item.name} (${item.formula})",
                                subtitle = item.category,
                                badge = "Compound",
                                badgeColor = Color(0xFF3B82F6),
                                onClick = { onCompoundClick(item.id) }
                            )
                        }
                        is SearchResultItem.ReactionResult -> {
                            SearchResultCard(
                                icon = Icons.Default.Science,
                                title = item.name,
                                subtitle = item.equation,
                                badge = "Reaction",
                                badgeColor = Color(0xFFF59E0B),
                                onClick = { onReactionClick(item.id) }
                            )
                        }
                        is SearchResultItem.ExperimentResult -> {
                            SearchResultCard(
                                icon = Icons.Default.Science,
                                title = item.title,
                                subtitle = item.category,
                                badge = "Lab Experiment",
                                badgeColor = Color(0xFF10B981),
                                onClick = { onExperimentClick(item.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchResultCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    badge: String,
    badgeColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = badgeColor, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFFF8FAFC))
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
            }
            Surface(shape = RoundedCornerShape(4.dp), color = badgeColor.copy(alpha = 0.2f)) {
                Text(text = badge, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall, color = badgeColor)
            }
        }
    }
}
