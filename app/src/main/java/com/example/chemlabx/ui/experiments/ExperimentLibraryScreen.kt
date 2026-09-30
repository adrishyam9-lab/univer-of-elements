package com.example.chemlabx.ui.experiments

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.chemlabx.data.ExperimentData
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.model.Experiment
import com.example.chemlabx.ui.components.ChemLabTopBar

@Composable
fun ExperimentLibraryScreen(
    onExperimentClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }
    val allExperiments = remember { ExperimentData.experiments }
    val categories = remember { listOf("All", "Acids & Bases", "Precipitation & Qualitative Analysis", "Redox & Metals", "Gas Evolution & Redox", "Catalysis & Thermochemistry") }

    val filteredExperiments by remember(selectedCategory) {
        derivedStateOf {
            if (selectedCategory == "All") allExperiments
            else allExperiments.filter { it.category.contains(selectedCategory, true) }
        }
    }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_experiments"),
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
            // Category Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
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
                        modifier = Modifier.testTag("exp_cat_$cat")
                    )
                }
            }

            // Experiments List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredExperiments, key = { it.id }) { experiment ->
                    ExperimentItemCard(
                        experiment = experiment,
                        onClick = { onExperimentClick(experiment.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ExperimentItemCard(
    experiment: Experiment,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("exp_card_${experiment.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF1E293B)
                ) {
                    Text(
                        text = experiment.category,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF38BDF8)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF10B981).copy(alpha = 0.2f)
                ) {
                    Text(
                        text = experiment.difficulty,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF10B981)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = experiment.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFFF8FAFC)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = experiment.objective,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${experiment.steps.size} Guided Steps • Post-Lab Quiz",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64748B)
                )

                Text(
                    text = "Launch Lab →",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
