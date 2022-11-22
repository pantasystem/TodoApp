package net.pantasystem.todoapp.impl

import net.pantasystem.todoapp.repository.AuthRepository
import platform.Foundation.NSUserDefaults


class AuthRepositoryImpl(
    private val userDefaults: NSUserDefaults
) : AuthRepository {

    override suspend fun getToken(): String? {
        return userDefaults.stringForKey("TOKEN").also {
            println("getToken:$it")
        }
    }

    override suspend fun saveToken(token: String?) {
        println("save token:$token")
        if (token == null) {
            userDefaults.removeObjectForKey("TOKEN")
        } else {
            userDefaults.setObject(token, "TOKEN")
        }
        userDefaults.synchronize()
    }
}