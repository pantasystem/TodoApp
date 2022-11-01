package net.pantasystem.todoandroidexample.impl

import net.pantasystem.todoandroidexample.ApiProvider
import net.pantasystem.todoandroidexample.api.CreateTaskRequest
import net.pantasystem.todoandroidexample.api.Task
import net.pantasystem.todoandroidexample.domain.AuthRepository
import net.pantasystem.todoandroidexample.domain.TaskRepository
import javax.inject.Inject


class TaskRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val apiProvider: ApiProvider,
) : TaskRepository {
    override suspend fun findTasks(): Result<List<Task>> = runCatching {
        val token = authRepository.getToken()
        apiProvider.getTasksApi().apply {
            setBearerToken(token ?: "")
        }.getTasks().body()
    }

    override suspend fun create(title: String, description: String?): Result<Unit> = runCatching {
        val token = authRepository.getToken()
        apiProvider.getTasksApi().apply {
            setBearerToken(token ?: "")
        }.createTask(CreateTaskRequest(title = title, description = description)).body()
    }

    override suspend fun completeTask(taskId: Long): Result<Unit> = runCatching {
        val token = authRepository.getToken()
        apiProvider.getTasksApi().apply {
            setAccessToken(token ?: "")
        }.completeTask(taskId)
    }

    override suspend fun delete(taskId: Long): Result<Unit> = runCatching {
        val token = authRepository.getToken()
        apiProvider.getTasksApi().apply {
            setAccessToken(token ?: "")
        }.deleteTask(taskId)
    }

    override suspend fun findOne(taskId: Long): Result<Task> = runCatching {
        val token = authRepository.getToken()
        apiProvider.getTasksApi().apply {
            setAccessToken(token ?: "")
        }.getTask(taskId).body()
    }
}