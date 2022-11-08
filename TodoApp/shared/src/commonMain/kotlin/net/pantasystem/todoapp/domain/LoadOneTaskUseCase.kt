package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class LoadOneTaskUseCase(
    val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskId: Long): Result<Task> {
        return taskRepository.findOne(taskId)
    }
}