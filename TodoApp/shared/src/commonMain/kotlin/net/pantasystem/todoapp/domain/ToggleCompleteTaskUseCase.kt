package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class ToggleCompleteTaskUseCase(
    val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task): Result<List<Task>> {
        return if (task.completedAt == null) {
            taskRepository.completeTask(task.id)
        } else {
            taskRepository.uncompleteTask(task.id)
        }.mapCatching {
            taskRepository.findTasks().getOrThrow()
        }
    }
}