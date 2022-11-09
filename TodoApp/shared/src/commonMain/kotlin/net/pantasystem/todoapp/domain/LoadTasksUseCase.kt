package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class LoadTasksUseCase(
    val taskRepository: TaskRepository
) : UseCase0Returns<List<Task>> {

    override suspend fun invoke(): List<Task> {
        return taskRepository.findTasks().getOrThrow()
    }
}