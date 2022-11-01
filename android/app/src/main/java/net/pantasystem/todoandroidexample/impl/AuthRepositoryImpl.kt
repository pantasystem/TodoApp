package net.pantasystem.todoandroidexample.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import net.pantasystem.todoandroidexample.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPref: SharedPreferences
): AuthRepository {

    override suspend fun getToken(): String? {
        return sharedPref.getString("TOKEN", null)
    }

    override suspend fun saveToken(token: String?) {
        sharedPref.edit {
            putString("TOKEN", token)
        }
    }
}