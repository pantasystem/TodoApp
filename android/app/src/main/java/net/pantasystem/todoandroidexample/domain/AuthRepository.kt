package net.pantasystem.todoandroidexample.domain

interface AuthRepository {
    suspend fun getToken(): String?

    suspend fun saveToken(token: String?)
}