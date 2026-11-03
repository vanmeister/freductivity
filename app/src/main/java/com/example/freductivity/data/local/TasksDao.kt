package com.example.freductivity.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.freductivity.domain.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = IGNORE)
    suspend fun addTask(taskDbModel: TaskDbModel)

    @Query("DELETE FROM tasks WHERE id == :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("UPDATE tasks SET title = :title WHERE id == :taskId")
    suspend fun editTaskTitle(taskId: Int, title: String)

    @Query("UPDATE tasks SET isDone = NOT isDone WHERE id == :taskId")
    suspend fun switchDoneStatus(taskId: Int)

    @Query("SELECT * FROM tasks WHERE id == :taskId")
    suspend fun getTask(taskId: Int): TaskDbModel

    @Query("SELECT * FROM tasks ORDER BY isDone DESC, id DESC")
    fun getAllTasks(): Flow<List<TaskDbModel>>
}