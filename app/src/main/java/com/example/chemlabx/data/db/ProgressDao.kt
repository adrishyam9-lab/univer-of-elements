package com.example.chemlabx.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM student_progress WHERE id = 1")
    fun getProgress(): Flow<StudentProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: StudentProgress)

    @Query("SELECT * FROM lab_log_entry ORDER BY timestamp DESC")
    fun getLabLogs(): Flow<List<LabLogEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(entry: LabLogEntry)
}
