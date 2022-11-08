package net.pantasystem.todoapp.impl

import android.content.SharedPreferences
import net.pantasystem.todoapp.repository.AuthRepository

class AuthRepositoryImpl(
    private val sharedPref: SharedPreferences
) : AuthRepository {

    override suspend fun getToken(): String? {
        return sharedPref.getString("TOKEN", null)
    }

    override suspend fun saveToken(token: String?) {
        val editor = sharedPref.edit()
        editor.putString("TOKEN", token)
        editor.apply()
    }
}