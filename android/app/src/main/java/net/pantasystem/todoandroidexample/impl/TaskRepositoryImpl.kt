package net.pantasystem.todoandroidexample.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        withContext(Dispatchers.IO) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.getTasks().body()
        }
    }

    override suspend fun create(title: String, description: String?): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.createTask(CreateTaskRequest(title = title, description = description)).body()
        }
    }

    override suspend fun completeTask(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.completeTask(taskId)
        }
    }

    override suspend fun delete(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setAccessToken(token ?: "")
            }.deleteTask(taskId)
        }
    }

    override suspend fun findOne(taskId: Long): Result<Task> = runCatching {
        withContext(Dispatchers.IO) {
            val token = authRepository.getToken()
            apiProvider.getTasksApi().apply {
                setBearerToken(token ?: "")
            }.getTask(taskId).body()
        }
    }

    override suspend fun uncompleteTask(taskId: Long): Result<Unit> = runCatching {
        withContext(Dispatchers.IO) {
            apiProvider.getTasksApi().apply {
                setBearerToken(authRepository.getToken() ?: "")
            }.uncompleteTask(taskId)
        }
    }
}