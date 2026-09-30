package com.example.chemlabx.ui.molecules

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.Text
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
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.model.Compound
import com.example.chemlabx.ui.components.ChemLabTopBar

@Composable
fun MoleculeExplorerScreen(
    onCompoundClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val allCompounds = remember { CompoundData.compounds }
    val categories = remember { listOf("All", "Inorganic", "Organic", "Acid", "Base", "Salt") }

    val filteredCompounds by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            allCompounds.filter { c ->
                val matchesQuery = searchQuery.isBlank() ||
                        c.name.contains(searchQuery, true) ||
                        c.formula.contains(searchQuery, true) ||
                        c.iupacName.contains(searchQuery, true) ||
                        c.category.contains(searchQuery, true)
                val matchesCat = selectedCategory == "All" || c.category.contains(selectedCategory, true)
                matchesQuery && matchesCat
            }
        }
    }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_molecules"),
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
            // Search field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("molecule_search_field"),
                placeholder = { Text("Search by name, formula (e.g. H₂O, NaCl)...", fontSize = 13.sp) },
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

            // Category Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = cat },
                        label = { Text(cat, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.testTag("mol_cat_$cat")
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Compound Cards List with 3D preview
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredCompounds, key = { it.id }) { compound ->
                    CompoundListItemCard(
                        compound = compound,
                        onClick = { onCompoundClick(compound.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CompoundListItemCard(
    compound: Compound,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("compound_card_${compound.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = compound.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Text(
                        text = compound.formula,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF1E293B)
                ) {
                    Text(
                        text = compound.category,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mini 3D canvas preview
            Molecule3DCanvas(
                compound = compound,
                viewMode = MoleculeViewMode.BALL_AND_STICK,
                isAutoRotate = false,
                modifier = Modifier.height(160.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Geometry: ${compound.geometry}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )
                Text(
                    text = "Molar Mass: ${compound.molarMass} g/mol",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFF59E0B),
                    fontSize = 11.sp
                )
            }
        }
    }
}
