package net.pantasystem.todobackend.account

data class Account(
    val name: String,
    val token: String,
    val id: Long = 0L
)