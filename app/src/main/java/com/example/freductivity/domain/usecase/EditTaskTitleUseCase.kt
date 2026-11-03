package com.example.freductivity.domain.usecase

import com.example.freductivity.domain.repository.TaskRepository
import javax.inject.Inject

class EditTaskTitleUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int, title: String) {
        taskRepository.editTaskTitle(taskId, title)
    }
}