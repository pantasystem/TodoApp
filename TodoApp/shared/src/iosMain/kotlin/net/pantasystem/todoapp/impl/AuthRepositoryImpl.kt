package net.pantasystem.todoapp.impl

import net.pantasystem.todoapp.repository.AuthRepository
import platform.Foundation.NSUserDefaults

class AuthRepositoryImpl(
    private val userDefaults: NSUserDefaults
) : AuthRepository {

    override suspend fun getToken(): String? {
        return userDefaults.stringForKey("TOKEN")
    }

    override suspend fun saveToken(token: String?) {
        if (token == null) {
            userDefaults.removeObjectForKey("TOKEN")
        } else {
            userDefaults.setObject("TOKEN", token)
        }
        userDefaults.synchronize()
    }
}