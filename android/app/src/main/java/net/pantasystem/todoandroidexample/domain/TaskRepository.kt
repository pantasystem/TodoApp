package net.pantasystem.todoandroidexample.domain

import net.pantasystem.todoandroidexample.api.Task

interface TaskRepository {

    suspend fun findTasks(): Result<List<Task>>

    suspend fun findOne(taskId: Long): Result<Task>

    suspend fun create(title: String, description: String? = null): Result<Unit>

    suspend fun completeTask(taskId: Long): Result<Unit>

    suspend fun delete(taskId: Long): Result<Unit>

}