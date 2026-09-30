package com.example.chemlabx.data.model

data class QuizQuestion(
    val id: String,
    val category: String, // Periodic Trends, Chemical Bonding, Reaction Types, Balancing, Solutions & pH
    val difficulty: String, // Middle School, Class 9-10, Class 11-12, Undergraduate
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class LabChallenge(
    val id: String,
    val title: String,
    val mysteryDescription: String,
    val clues: List<String>,
    val possibleAnswers: List<String>,
    val correctAnswerIndex: Int,
    val resolutionExplanation: String
)
