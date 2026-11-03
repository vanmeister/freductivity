package com.example.freductivity.domain.usecase

import com.example.freductivity.domain.repository.TaskRepository
import javax.inject.Inject

class SwitchDoneStatusUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskId: Int) {
        taskRepository.switchDoneStatus(taskId)
    }
}