package com.example.chemlabx.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_progress")
data class StudentProgress(
    @PrimaryKey val id: Int = 1,
    val streakDays: Int = 3,
    val quizScore: Int = 240,
    val totalQuizzesAnswered: Int = 12,
    val exploredElements: String = "1,6,8,11,17,26,29,79", // Comma-separated atomic numbers
    val exploredCompounds: String = "water,carbon_dioxide,sodium_chloride",
    val studiedReactions: String = "neutralization_hcl_naoh,single_displacement_iron_copper",
    val completedExperiments: String = "exp_acid_base_titration,exp_golden_rain",
    val studentLevel: String = "Class 9–10"
)

@Entity(tableName = "lab_log_entry")
data class LabLogEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val title: String,
    val observation: String,
    val tag: String // EXPERIMENT, REACTION, FREE_LAB, NOTE
)
