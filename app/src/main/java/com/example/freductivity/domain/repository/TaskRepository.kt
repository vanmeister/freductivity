package com.example.freductivity.domain.repository

import com.example.freductivity.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(
        taskId: Int,
        title: String,
        isDone: Boolean
    )

    suspend fun deleteTask(taskId: Int)

    suspend fun editTaskTitle(taskId: Int, title: String)

    suspend fun switchDoneStatus(taskId: Int)

    suspend fun getTask(taskId: Int): Task

    fun getAllTasks(): Flow<List<Task>>
}