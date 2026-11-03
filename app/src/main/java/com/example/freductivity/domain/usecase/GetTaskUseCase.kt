package com.example.freductivity.domain.usecase

import com.example.freductivity.domain.entity.Task
import com.example.freductivity.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int): Task {
        return taskRepository.getTask(taskId)
    }
}