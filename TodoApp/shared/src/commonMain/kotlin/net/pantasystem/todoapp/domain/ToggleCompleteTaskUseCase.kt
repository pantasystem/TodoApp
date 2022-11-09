package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class ToggleCompleteTaskUseCase(
    val taskRepository: TaskRepository
) : UseCaseReturns<Task, List<Task>> {
    override suspend fun invoke(args: Task): List<Task> {
        return if (args.completedAt == null) {
            taskRepository.completeTask(args.id)
        } else {
            taskRepository.uncompleteTask(args.id)
        }.mapCatching {
            taskRepository.findTasks().getOrThrow()
        }.getOrThrow()
    }
}