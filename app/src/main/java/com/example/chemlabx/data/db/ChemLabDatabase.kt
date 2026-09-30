package com.example.chemlabx.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentProgress::class, LabLogEntry::class], version = 1, exportSchema = false)
abstract class ChemLabDatabase : RoomDatabase() {
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: ChemLabDatabase? = null

        fun getDatabase(context: Context): ChemLabDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChemLabDatabase::class.java,
                    "chemlab_database"
                ).fallbackToDestructiveMigration(true).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
