package net.pantasystem.todoapp.repository

interface AuthRepository {
    suspend fun getToken(): String?

    suspend fun saveToken(token: String?)
}