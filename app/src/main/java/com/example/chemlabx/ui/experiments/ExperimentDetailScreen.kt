package com.example.chemlabx.ui.experiments

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Science
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.ExperimentData
import com.example.chemlabx.data.model.Experiment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperimentDetailScreen(
    experimentId: String,
    onBackClick: () -> Unit
) {
    val experiment: Experiment = remember(experimentId) {
        ExperimentData.experiments.firstOrNull { it.id == experimentId }
            ?: ExperimentData.experiments.first()
    }

    var currentStepIndex by remember { mutableIntStateOf(0) }
    val executedSteps = remember { mutableStateListOf<Int>() }
    var selectedQuizAnswer by remember { mutableStateOf<Int?>(null) }
    var hasAnsweredQuiz by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(experiment.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBackClick, modifier = Modifier.testTag("exp_detail_back")) {
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
            // Objective & Meta Card
            Card(
                modifier = Modifier.fillMaxWidth(),
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
                        text = "Laboratory Objective",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFFF8FAFC)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = experiment.objective,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFCBD5E1),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Required Materials: " + experiment.materials.joinToString(", "),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            // Step Progress Timeline
            Text(
                text = "Interactive Procedure (${executedSteps.size} of ${experiment.steps.size} Completed)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            experiment.steps.forEachIndexed { index, step ->
                val isExecuted = executedSteps.contains(index)
                val isCurrent = currentStepIndex == index

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("step_card_$index"),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCurrent) Color(0xFF1E293B) else Color(0xFF0F172A)
                    ),
                    border = if (isCurrent) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF38BDF8))) else null
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(if (isExecuted) Color(0xFF10B981) else Color(0xFF334155)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isExecuted) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                } else {
                                    Text("${index + 1}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = step.title,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = step.instruction,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFE2E8F0),
                            lineHeight = 20.sp
                        )

                        // Action button for this step
                        if (!isExecuted && (isCurrent || index == 0)) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    executedSteps.add(index)
                                    if (currentStepIndex < experiment.steps.lastIndex) {
                                        currentStepIndex = index + 1
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("execute_step_button_$index"),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Execute Step: ${step.title}", fontWeight = FontWeight.Bold)
                            }
                        }

                        // Observation readout after execution
                        if (isExecuted) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF0F172A),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text("Observation: ", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFFF59E0B))
                                    Text(step.intermediateObservation, style = MaterialTheme.typography.bodySmall, color = Color(0xFFCBD5E1))
                                }
                            }
                        }
                    }
                }
            }

            // Post-Experiment Completion & Explanation (when all steps completed)
            if (executedSteps.size == experiment.steps.size) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B).copy(alpha = 0.4f)),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF10B981)))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF10B981))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Experiment Completed Successfully!",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF10B981)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Balanced Chemical Equation:", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF0F172A),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = experiment.balancedEquation,
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Scientific Explanation:", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                        Text(
                            text = experiment.scientificExplanation,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFE2E8F0),
                            lineHeight = 18.sp
                        )
                    }
                }

                // Post-Lab Quiz Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Quiz, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Post-Lab Comprehension Check",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = experiment.quizQuestion,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFFF8FAFC)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        experiment.quizOptions.forEachIndexed { optIndex, optionText ->
                            val isSelected = selectedQuizAnswer == optIndex
                            val isCorrect = optIndex == experiment.correctQuizIndex

                            val bgColor = when {
                                hasAnsweredQuiz && isCorrect -> Color(0xFF065F46)
                                hasAnsweredQuiz && isSelected && !isCorrect -> Color(0xFF991B1B)
                                isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                else -> Color(0xFF1E293B)
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = bgColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable {
                                        if (!hasAnsweredQuiz) {
                                            selectedQuizAnswer = optIndex
                                            hasAnsweredQuiz = true
                                        }
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${'A' + optIndex}. ",
                                        fontWeight = FontWeight.Bold,
                                        color = if (hasAnsweredQuiz && isCorrect) Color(0xFF34D399) else Color(0xFF94A3B8)
                                    )
                                    Text(
                                        text = optionText,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFFF1F5F9)
                                    )
                                }
                            }
                        }

                        if (hasAnsweredQuiz) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = experiment.quizExplanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (selectedQuizAnswer == experiment.correctQuizIndex) Color(0xFF34D399) else Color(0xFFFCA5A5),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
