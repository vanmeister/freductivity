package com.example.freductivity.data.mapper

import com.example.freductivity.data.local.TaskDbModel
import com.example.freductivity.domain.entity.Task

fun List<TaskDbModel>.toEntities(): List<Task> {
    return map {
        Task(
            id = it.id,
            title = it.title,
            isDone = it.isDone,
        )
    }
}