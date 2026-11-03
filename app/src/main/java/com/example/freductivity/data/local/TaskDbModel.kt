package com.example.freductivity.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks"
)
data class TaskDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val isDone: Boolean,
)