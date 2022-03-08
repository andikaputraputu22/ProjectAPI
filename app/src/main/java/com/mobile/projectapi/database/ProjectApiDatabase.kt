package com.mobile.projectapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProjectApiEntity::class],
    exportSchema = false,
    version = 1
)

abstract class ProjectApiDatabase: RoomDatabase() {

    abstract fun projectApiDao(): ProjectApiDao

    companion object {
        @Volatile private var instance: ProjectApiDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also{ instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ProjectApiDatabase::class.java, "projectapi.db")
                .allowMainThreadQueries()
                .build()
    }
}