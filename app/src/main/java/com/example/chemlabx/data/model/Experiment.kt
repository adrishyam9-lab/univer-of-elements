package com.example.chemlabx.data.model

data class ExperimentStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val actionSubstance: String? = null,
    val actionEquipment: String? = null,
    val intermediateObservation: String
)

data class Experiment(
    val id: String,
    val title: String,
    val category: String, // Acids & Bases, Redox, Salts, Metal Activity, Thermochemistry, etc.
    val difficulty: String, // Beginner, Class 9-10, Class 11-12, Undergrad
    val objective: String,
    val materials: List<String>,
    val equipmentRequired: List<String>,
    val steps: List<ExperimentStep>,
    val balancedEquation: String,
    val expectedObservation: String,
    val scientificExplanation: String,
    val quizQuestion: String,
    val quizOptions: List<String>,
    val correctQuizIndex: Int,
    val quizExplanation: String
)
