package com.example.freductivity.presentation.screens.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.freductivity.R
import com.example.freductivity.domain.entity.Task
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var title by rememberSaveable { mutableStateOf("") }

    val closeModal: () -> Unit = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
            }
            title = ""
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TasksTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet = true }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 20.dp),
                    text = stringResource(R.string.new_task),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(16.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = state.value.currentTasks,
                key = { task -> task.id }
            ) { task ->
                TaskElement(
                    task = task,
                    switchDoneStatus = {
                        viewModel.processCommand(TasksCommand.SwitchDoneStatus(task.id))
                    }
                )
            }
            item {
                Text(
                    text = stringResource(
                        R.string.done_count_tasks_screen,
                        state.value.finishedTasksCount
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            items(
                items = state.value.finishedTasks,
                key = { task -> task.id }
            ) { task ->
                TaskElement(
                    task = task,
                    switchDoneStatus = {
                        viewModel.processCommand(TasksCommand.SwitchDoneStatus(task.id))
                    }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                    title = ""
                },
                sheetState = sheetState,
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = stringResource(R.string.new_task_modal),
                        style = MaterialTheme.typography.titleLarge
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = { title = it },
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                closeModal()
                            },
                        ) {
                            Text(
                                text = stringResource(R.string.cancel_modal),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Button(
                            onClick = {
                                viewModel.processCommand(TasksCommand.AddTask(title))
                                closeModal()
                            },
                            enabled = title.isNotBlank()
                        ) {
                            Text(
                                stringResource(R.string.add_task_modal),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.today_topbar),
                style = MaterialTheme.typography.headlineLarge
            )
        }
    )
}

@Composable
fun TaskElement(
    modifier: Modifier = Modifier,
    task: Task,
    switchDoneStatus: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = {
                    switchDoneStatus()
                }
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
        }
    }
}