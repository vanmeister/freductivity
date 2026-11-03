package com.example.freductivity.domain.usecase

import com.example.freductivity.domain.entity.Task
import com.example.freductivity.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}