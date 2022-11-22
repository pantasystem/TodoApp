package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.TaskRepository

class CreateTaskUseCase(
    val taskRepository: TaskRepository
) : UseCaseReturns<CreateTask, List<Task>>{

    override suspend operator fun invoke(args: CreateTask): List<Task> {
        return taskRepository.create(args.title, args.description).mapCatching {
            taskRepository.findTasks().getOrThrow()
        }.onFailure {
            println("error:$it")
        }.getOrThrow()
    }
}

data class CreateTask(val title: String, val description: String?)