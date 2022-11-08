package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class LoadTasksUseCase(
    val taskRepository: TaskRepository
) {

    suspend operator fun invoke(): Result<List<Task>> {
        return taskRepository.findTasks()
    }
}