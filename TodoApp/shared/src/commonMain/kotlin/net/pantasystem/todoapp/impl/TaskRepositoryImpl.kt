package net.pantasystem.todoapp.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.pantasystem.todoapp.ApiProvider
import net.pantasystem.todoapp.api.CreateTaskRequest
import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.repository.AuthRepository
import net.pantasystem.todoapp.repository.TaskRepository


class TaskRepositoryImpl(
    private val authRepository: AuthRepository,
    private val apiProvider: ApiProvider,
) : TaskRepository {
    override suspend fun findTasks(): Result<List<Task>> = runCatching {
        withContext(Dispatchers.Default) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.getTasks().body()
        }
    }

    override suspend fun create(title: String, description: String?): Result<Unit> = runCatching {
        withContext(Dispatchers.Default) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.createTask(CreateTaskRequest(title = title, description = description)).body()
        }
    }

    override suspend fun completeTask(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.Default) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.completeTask(taskId)
        }
    }

    override suspend fun delete(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.Default) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setAccessToken(token ?: "")
            }.deleteTask(taskId)
        }
    }

    override suspend fun findOne(taskId: Long): Result<Task> = runCatching {
        withContext(Dispatchers.Default) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.getTask(taskId).body()
        }
    }

    override suspend fun uncompleteTask(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.Default) {
            apiProvider.getTasksApi().apply {
                setBearerToken(authRepository.getToken() ?: "")
            }.uncompleteTask(taskId)
        }
    }
}