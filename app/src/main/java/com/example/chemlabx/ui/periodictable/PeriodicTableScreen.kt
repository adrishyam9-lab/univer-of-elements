package com.example.chemlabx.ui.periodictable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ViewModule
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.ElementData
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.model.Element
import com.example.chemlabx.data.model.ElementCategory
import com.example.chemlabx.ui.components.ChemLabTopBar

@Composable
fun PeriodicTableScreen(
    onElementClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ElementCategory?>(null) }
    var selectedViewTabIndex by remember { mutableIntStateOf(0) } // 0: Grid (Standard Periodic Table), 1: List View

    val allElements = remember { ElementData.allElements }

    val filteredElements by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            allElements.filter { el ->
                val matchesSearch = searchQuery.isBlank() ||
                        el.name.contains(searchQuery, ignoreCase = true) ||
                        el.symbol.equals(searchQuery, ignoreCase = true) ||
                        el.number.toString() == searchQuery.trim() ||
                        el.electronConfiguration.contains(searchQuery, ignoreCase = true)
                val matchesCategory = selectedCategory == null || el.category == selectedCategory
                matchesSearch && matchesCategory
            }
        }
    }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_periodic"),
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
            // View Mode Tab (Interactive 18-Column Grid vs Categorized List)
            TabRow(
                selectedTabIndex = selectedViewTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedViewTabIndex]),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                Tab(
                    selected = selectedViewTabIndex == 0,
                    onClick = { selectedViewTabIndex = 0 },
                    text = { Text("Standard 18-Group Table", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("tab_grid_view")
                )
                Tab(
                    selected = selectedViewTabIndex == 1,
                    onClick = { selectedViewTabIndex = 1 },
                    text = { Text("Search & Element List", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("tab_list_view")
                )
            }

            // Search Bar & Clear
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("element_search_field"),
                placeholder = { Text(LocalizationManager.getString("search_placeholder"), fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear search")
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

            // Category Filter Chips Carousel
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text(LocalizationManager.getString("filter_all")) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.testTag("filter_all")
                    )
                }
                items(ElementCategory.entries) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = {
                            selectedCategory = if (selectedCategory == cat) null else cat
                        },
                        label = { Text(cat.displayName, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(cat.colorHex).copy(alpha = 0.3f),
                            selectedLabelColor = Color(cat.colorHex)
                        ),
                        modifier = Modifier.testTag("filter_cat_${cat.name}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (selectedViewTabIndex == 0) {
                // Interactive 18-Group Periodic Table Grid with horizontal scrolling
                PeriodicTableInteractiveGrid(
                    elements = allElements,
                    searchQuery = searchQuery,
                    selectedCategory = selectedCategory,
                    onElementClick = onElementClick
                )
            } else {
                // Element Card List View
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredElements, key = { it.number }) { element ->
                        ElementRowCard(element = element, onClick = { onElementClick(element.number) })
                    }
                }
            }
        }
    }
}

@Composable
private fun PeriodicTableInteractiveGrid(
    elements: List<Element>,
    searchQuery: String,
    selectedCategory: ElementCategory?,
    onElementClick: (Int) -> Unit
) {
    val hScrollState = rememberScrollState()
    val vScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .horizontalScroll(hScrollState)
    ) {
        // Group Header Numbers (1 to 18)
        Row {
            Spacer(modifier = Modifier.width(28.dp)) // Space for period numbers
            for (g in 1..18) {
                Text(
                    text = "$g",
                    modifier = Modifier.width(52.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF64748B)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Periods 1 to 7
        for (p in 1..7) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Period label
                Text(
                    text = "$p",
                    modifier = Modifier.width(28.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF64748B)
                )

                // 18 Groups in this period
                for (g in 1..18) {
                    val elem = elements.firstOrNull { it.period == p && it.group == g }
                    if (elem != null) {
                        val isHighlighted = (searchQuery.isBlank() || elem.name.contains(searchQuery, true) || elem.symbol.equals(searchQuery, true)) &&
                                (selectedCategory == null || elem.category == selectedCategory)
                        PeriodicCell(
                            element = elem,
                            isDimmed = !isHighlighted,
                            onClick = { onElementClick(elem.number) }
                        )
                    } else if (p == 6 && g == 3) {
                        // Lanthanides placeholder cell (57-71)
                        SpecialSeriesPlaceholderCell(text = "57–71\nLa–Lu", color = Color(0xFFEC4899))
                    } else if (p == 7 && g == 3) {
                        // Actinides placeholder cell (89-103)
                        SpecialSeriesPlaceholderCell(text = "89–103\nAc–Lr", color = Color(0xFFD946EF))
                    } else {
                        // Empty slot in periodic table
                        Spacer(modifier = Modifier.size(52.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Lanthanide Row (Period 6, f-block 57-71)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "La*",
                modifier = Modifier.width(28.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFEC4899)
            )
            Spacer(modifier = Modifier.width(52.dp * 2)) // offset under transition metals
            val lanthanides = elements.filter { it.category == ElementCategory.LANTHANIDE }
            lanthanides.forEach { elem ->
                PeriodicCell(
                    element = elem,
                    isDimmed = selectedCategory != null && selectedCategory != ElementCategory.LANTHANIDE,
                    onClick = { onElementClick(elem.number) }
                )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        // Actinide Row (Period 7, f-block 89-103)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Ac**",
                modifier = Modifier.width(28.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD946EF)
            )
            Spacer(modifier = Modifier.width(52.dp * 2))
            val actinides = elements.filter { it.category == ElementCategory.ACTINIDE }
            actinides.forEach { elem ->
                PeriodicCell(
                    element = elem,
                    isDimmed = selectedCategory != null && selectedCategory != ElementCategory.ACTINIDE,
                    onClick = { onElementClick(elem.number) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun PeriodicCell(
    element: Element,
    isDimmed: Boolean,
    onClick: () -> Unit
) {
    val catColor = Color(element.category.colorHex)
    val bgColor = if (isDimmed) Color(0xFF1E293B).copy(alpha = 0.3f) else catColor.copy(alpha = 0.22f)
    val borderColor = if (isDimmed) Color(0xFF334155).copy(alpha = 0.4f) else catColor

    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(1.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .testTag("element_cell_${element.symbol}"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Text(
                text = "${element.number}",
                fontSize = 8.sp,
                color = if (isDimmed) Color(0xFF64748B) else Color(0xFF94A3B8),
                lineHeight = 9.sp
            )
            Text(
                text = element.symbol,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDimmed) Color(0xFF94A3B8) else Color.White,
                lineHeight = 15.sp
            )
            Text(
                text = element.name,
                fontSize = 7.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (isDimmed) Color(0xFF64748B) else Color(0xFFCBD5E1),
                lineHeight = 8.sp
            )
        }
    }
}

@Composable
private fun SpecialSeriesPlaceholderCell(text: String, color: Color) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(1.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 8.sp,
            textAlign = TextAlign.Center,
            color = color,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ElementRowCard(
    element: Element,
    onClick: () -> Unit
) {
    val catColor = Color(element.category.colorHex)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("element_card_${element.number}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0F172A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Element Symbol Badge
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(catColor.copy(alpha = 0.25f))
                    .border(1.5.dp, catColor, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${element.number}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF94A3B8),
                        fontSize = 9.sp
                    )
                    Text(
                        text = element.symbol,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Name, Category, Mass, Config
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = element.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = catColor.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = element.category.displayName,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = catColor,
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Mass: ${element.atomicMass} u • Config: ${element.electronConfiguration}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )

                Text(
                    text = "Phase: ${element.phase.label} • Group: ${element.group ?: "f-block"}, Period: ${element.period}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF64748B),
                    fontSize = 11.sp
                )
            }
        }
    }
}
