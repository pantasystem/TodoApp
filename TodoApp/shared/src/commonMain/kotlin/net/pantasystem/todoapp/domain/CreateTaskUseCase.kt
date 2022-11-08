package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class CreateTaskUseCase(
    val taskRepository: TaskRepository
) {

    suspend operator fun invoke(title: String, description: String?): Result<List<Task>> {
        return taskRepository.create(title, description).mapCatching {
            taskRepository.findTasks().getOrThrow()
        }
    }
}