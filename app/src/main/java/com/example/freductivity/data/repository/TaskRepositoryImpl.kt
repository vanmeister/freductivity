package com.example.freductivity.data.repository

import com.example.freductivity.data.local.TaskDbModel
import com.example.freductivity.data.local.TasksDao
import com.example.freductivity.domain.entity.Task
import com.example.freductivity.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao,
) : TaskRepository {
    override suspend fun addTask(taskId: Int, title: String, isDone: Boolean) {
        tasksDao.addTask(TaskDbModel(0, title, isDone))
    }

    override suspend fun deleteTask(taskId: Int) {
        tasksDao.deleteTask(taskId)
    }

    override suspend fun editTaskTitle(taskId: Int, title: String) {
        tasksDao.editTaskTitle(taskId, title)
    }

    override suspend fun switchDoneStatus(taskId: Int) {
        tasksDao.switchDoneStatus(taskId)
    }

    override suspend fun getTask(taskId: Int): Task {
        val task = tasksDao.getTask(taskId)
        return Task(id = task.id, title = task.title, isDone = task.isDone)
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return tasksDao.getAllTasks()
    }
}