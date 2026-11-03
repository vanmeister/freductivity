package com.example.freductivity.presentation.screens.tasks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.freductivity.domain.entity.Task

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TasksTopBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(16.dp),
            contentPadding = innerPadding
        ) {
            items(
                items = state.value.tasks,
                key = { task -> task.id }
            ) { task ->
                Task(task = task)
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
            Text(text = "Сегодня")
        }
    )
}

@Composable
fun Task(
    modifier: Modifier = Modifier,
    task: Task
) {
    Text(text = task.title)
}