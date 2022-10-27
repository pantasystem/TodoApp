package net.pantasystem.todobackend.task

import java.time.Instant


data class Task(

    val title: String,
    val description: String?,
    val accountId: Long,
    val completedAt: Instant? = null,
    val updatedAt: Instant? = null,
    val createdAt: Instant? = null,
    val id: Long = 0L,
)