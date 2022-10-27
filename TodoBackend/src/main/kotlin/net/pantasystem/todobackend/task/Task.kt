package net.pantasystem.todobackend.task

data class Task(

    val title: String,
    val description: String?,
    val accountId: Long,
    val id: Long = 0L,
)