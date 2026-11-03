package com.example.freductivity.domain.usecase

import com.example.freductivity.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(title: String) {
        taskRepository.addTask(title)
    }
}