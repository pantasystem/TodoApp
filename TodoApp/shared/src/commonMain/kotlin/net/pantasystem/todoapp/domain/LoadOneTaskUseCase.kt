package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class LoadOneTaskUseCase(
    val taskRepository: TaskRepository
) : UseCaseReturns<Long, Task>{
    override suspend fun invoke(args: Long): Task {
        return taskRepository.findOne(args).getOrThrow()
    }
}