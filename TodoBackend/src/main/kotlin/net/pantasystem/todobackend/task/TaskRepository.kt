package net.pantasystem.todobackend.task

import org.springframework.stereotype.Repository

@Repository
interface TaskRepository {
    fun create(task: Task): Task
    fun update(task: Task)
    fun findByAccount(accountId: Long): List<Task>
    fun findOne(taskId: Long): Task
}