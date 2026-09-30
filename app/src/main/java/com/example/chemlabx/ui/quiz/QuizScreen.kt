package com.example.chemlabx.ui.quiz

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.QuizData
import com.example.chemlabx.ui.components.ChemLabTopBar

@Composable
fun QuizScreen(
    onSearchClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Quizzes, 1: Mystery Challenges
    var selectedLevel by remember { mutableStateOf("Class 9–10") }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }

    val levels = remember { listOf("Middle School", "Class 9–10", "Class 11–12", "Undergraduate") }
    val questions = remember(selectedLevel) {
        val filtered = QuizData.quizQuestions.filter { it.difficulty == selectedLevel }
        if (filtered.isNotEmpty()) filtered else QuizData.quizQuestions
    }

    val challenges = remember { QuizData.labChallenges }
    var currentChallengeIndex by remember { mutableIntStateOf(0) }
    var selectedChallengeAnswer by remember { mutableStateOf<Int?>(null) }
    var isChallengeSubmitted by remember { mutableStateOf(false) }

    val currentQ = questions.getOrElse(currentQuestionIndex) { questions.first() }
    val currentC = challenges.getOrElse(currentChallengeIndex) { challenges.first() }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = LocalizationManager.getString("nav_quiz"),
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
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Topic Quiz", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("quiz_tab_quiz")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Lab Mystery Challenges", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.testTag("quiz_tab_challenges")
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (selectedTab == 0) {
                    // Level selector chips
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Level: ", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(levels) { lvl ->
                                FilterChip(
                                    selected = selectedLevel == lvl,
                                    onClick = {
                                        selectedLevel = lvl
                                        currentQuestionIndex = 0
                                        selectedAnswerIndex = null
                                        isSubmitted = false
                                    },
                                    label = { Text(lvl, fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                    ),
                                    modifier = Modifier.testTag("lvl_$lvl")
                                )
                            }
                        }
                    }

                    // Score bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF94A3B8)
                        )
                        Text(
                            text = "Score: $score pts",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF10B981)
                        )
                    }

                    // Question Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFF1E293B)
                            ) {
                                Text(
                                    text = currentQ.category,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF38BDF8)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = currentQ.question,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC),
                                lineHeight = 24.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Multiple Choice Options
                            currentQ.options.forEachIndexed { idx, opt ->
                                val isSelected = selectedAnswerIndex == idx
                                val isCorrect = idx == currentQ.correctIndex

                                val optionBg = when {
                                    isSubmitted && isCorrect -> Color(0xFF065F46)
                                    isSubmitted && isSelected && !isCorrect -> Color(0xFF991B1B)
                                    isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                                    else -> Color(0xFF1E293B)
                                }

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = optionBg,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp)
                                        .clickable {
                                            if (!isSubmitted) {
                                                selectedAnswerIndex = idx
                                            }
                                        }
                                        .testTag("quiz_opt_$idx")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${'A' + idx}. ",
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSubmitted && isCorrect) Color(0xFF34D399) else Color(0xFF94A3B8)
                                        )
                                        Text(
                                            text = opt,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFFF1F5F9),
                                            modifier = Modifier.weight(1f)
                                        )
                                        if (isSubmitted && isCorrect) {
                                            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF34D399), modifier = Modifier.size(18.dp))
                                        } else if (isSubmitted && isSelected && !isCorrect) {
                                            Icon(Icons.Default.Close, contentDescription = null, tint = Color(0xFFF87171), modifier = Modifier.size(18.dp))
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            if (!isSubmitted) {
                                Button(
                                    onClick = {
                                        if (selectedAnswerIndex != null) {
                                            isSubmitted = true
                                            if (selectedAnswerIndex == currentQ.correctIndex) {
                                                score += 20
                                            }
                                        }
                                    },
                                    enabled = selectedAnswerIndex != null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("quiz_submit_button"),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                                ) {
                                    Text("Submit Answer", fontWeight = FontWeight.Bold)
                                }
                            } else {
                                // Detailed Explanation
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = if (selectedAnswerIndex == currentQ.correctIndex) "✓ Correct!" else "✗ Not quite!",
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                            color = if (selectedAnswerIndex == currentQ.correctIndex) Color(0xFF34D399) else Color(0xFFF87171)
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = currentQ.explanation,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color(0xFFE2E8F0),
                                            lineHeight = 18.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = {
                                        selectedAnswerIndex = null
                                        isSubmitted = false
                                        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("quiz_next_button"),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Next Question →", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                } else {
                    // Tab 1: Lab Mystery Challenges
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Psychology, contentDescription = null, tint = Color(0xFFF59E0B))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = currentC.title,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFFF8FAFC)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = currentC.mysteryDescription,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFE2E8F0),
                                lineHeight = 20.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Detective Clues:",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF38BDF8)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            currentC.clues.forEach { clue ->
                                Text("🔍 $clue", style = MaterialTheme.typography.bodySmall, color = Color(0xFFCBD5E1), modifier = Modifier.padding(vertical = 2.dp))
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "What is the identity of the chemical compound / reaction?",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF8FAFC)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            currentC.possibleAnswers.forEachIndexed { cIdx, ans ->
                                val isSelected = selectedChallengeAnswer == cIdx
                                val isCorrect = cIdx == currentC.correctAnswerIndex

                                val bg = when {
                                    isChallengeSubmitted && isCorrect -> Color(0xFF065F46)
                                    isChallengeSubmitted && isSelected && !isCorrect -> Color(0xFF991B1B)
                                    isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                                    else -> Color(0xFF1E293B)
                                }

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = bg,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            if (!isChallengeSubmitted) {
                                                selectedChallengeAnswer = cIdx
                                            }
                                        }
                                ) {
                                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Text("${cIdx + 1}. ", fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                                        Text(ans, style = MaterialTheme.typography.bodyMedium, color = Color(0xFFF1F5F9))
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            if (!isChallengeSubmitted) {
                                Button(
                                    onClick = {
                                        if (selectedChallengeAnswer != null) {
                                            isChallengeSubmitted = true
                                        }
                                    },
                                    enabled = selectedChallengeAnswer != null,
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                                ) {
                                    Text("Solve Mystery", fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color(0xFF1E293B),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = if (selectedChallengeAnswer == currentC.correctAnswerIndex) "✓ Mystery Solved!" else "✗ Case Unsolved!",
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                            color = if (selectedChallengeAnswer == currentC.correctAnswerIndex) Color(0xFF34D399) else Color(0xFFF87171)
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(currentC.resolutionExplanation, style = MaterialTheme.typography.bodySmall, color = Color(0xFFE2E8F0))
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = {
                                        selectedChallengeAnswer = null
                                        isChallengeSubmitted = false
                                        currentChallengeIndex = (currentChallengeIndex + 1) % challenges.size
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Next Lab Mystery →", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
