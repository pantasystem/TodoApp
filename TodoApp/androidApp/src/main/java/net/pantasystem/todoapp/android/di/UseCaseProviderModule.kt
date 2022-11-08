package net.pantasystem.todoapp.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.pantasystem.todoapp.domain.CreateTaskUseCase
import net.pantasystem.todoapp.domain.LoadOneTaskUseCase
import net.pantasystem.todoapp.domain.LoadTasksUseCase
import net.pantasystem.todoapp.domain.ToggleCompleteTaskUseCase
import net.pantasystem.todoapp.repository.TaskRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseProviderModule {

    @Provides
    @Singleton
    fun provideToggleCompleteTaskUseCase(
        taskRepository: TaskRepository
    ): ToggleCompleteTaskUseCase {
        return ToggleCompleteTaskUseCase(taskRepository)
    }

    @Provides
    @Singleton
    fun provideLoadTasksUseCase(
        taskRepository: TaskRepository
    ): LoadTasksUseCase {
        return LoadTasksUseCase(taskRepository)
    }

    @Provides
    @Singleton
    fun provideCreateUseCase(taskRepository: TaskRepository): CreateTaskUseCase {
        return CreateTaskUseCase(taskRepository)
    }

    @Provides
    @Singleton
    fun provideLoadOneTaskUseCase(taskRepository: TaskRepository): LoadOneTaskUseCase {
        return LoadOneTaskUseCase(taskRepository)
    }
}