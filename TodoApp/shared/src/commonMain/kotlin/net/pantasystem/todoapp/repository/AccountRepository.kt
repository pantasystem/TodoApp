package net.pantasystem.todoapp.repository

import net.pantasystem.todoapp.api.Account
import net.pantasystem.todoapp.api.TokenWithAccount

interface AccountRepository {

    suspend fun register(): Result<TokenWithAccount>

    suspend fun findSelf(): Result<Account>
}