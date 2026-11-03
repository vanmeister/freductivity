package com.example.freductivity.di

import android.content.Context
import androidx.room.Room
import com.example.freductivity.data.local.TasksDao
import com.example.freductivity.data.local.TasksDatabase
import com.example.freductivity.data.repository.TaskRepositoryImpl
import com.example.freductivity.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTasksRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): TasksDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = TasksDatabase::class.java,
                name = "tasks.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()
        }

        @Provides
        @Singleton
        fun provideTasksDao(
            tasksDatabase: TasksDatabase
        ): TasksDao {
            return tasksDatabase.tasksDao()
        }
    }
}