package com.example.freductivity.presentation.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freductivity.domain.entity.Task
import com.example.freductivity.domain.usecase.AddTaskUseCase
import com.example.freductivity.domain.usecase.GetAllTasksUseCase
import com.example.freductivity.domain.usecase.SwitchDoneStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val switchDoneStatusUseCase: SwitchDoneStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TasksState())

    val state = _state.asStateFlow()


    init {
        getAllTasksUseCase()
            .onEach { tasks ->
                _state.update { previousState ->
                    previousState.copy(tasks = tasks)
                }
            }.launchIn(viewModelScope)
    }

    fun processCommand(command: TasksCommand) {
        viewModelScope.launch {
            when (command) {
                is TasksCommand.AddTask -> {
                    addTaskUseCase(command.title.trim())
                }

                is TasksCommand.SwitchDoneStatus -> {
                    switchDoneStatusUseCase(command.taskId)
                }
            }
        }
    }
}


sealed interface TasksCommand {

    data class AddTask(val title: String) : TasksCommand
    data class SwitchDoneStatus(val taskId: Int) : TasksCommand
}


data class TasksState(
    val tasks: List<Task> = listOf(),
)