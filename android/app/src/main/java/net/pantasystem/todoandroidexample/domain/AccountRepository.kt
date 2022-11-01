package net.pantasystem.todoandroidexample.domain

import net.pantasystem.todoandroidexample.api.Account
import net.pantasystem.todoandroidexample.api.TokenWithAccount

interface AccountRepository {

    suspend fun register(): Result<TokenWithAccount>

    suspend fun findSelf(): Result<Account>
}