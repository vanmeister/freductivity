package com.example.freductivity.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class TasksDatabase: RoomDatabase() {

    abstract fun tasksDao(): TasksDao
}