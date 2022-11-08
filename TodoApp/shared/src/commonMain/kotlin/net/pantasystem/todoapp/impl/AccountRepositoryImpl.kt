package net.pantasystem.todoapp.impl

import net.pantasystem.todoapp.ApiProvider
import net.pantasystem.todoapp.api.Account
import net.pantasystem.todoapp.api.TokenWithAccount
import net.pantasystem.todoapp.repository.AccountRepository
import net.pantasystem.todoapp.repository.AuthRepository

class AccountRepositoryImpl constructor(
    private val authRepository: AuthRepository,
    private val apiProvider: ApiProvider,
) : AccountRepository {
    override suspend fun register(): Result<TokenWithAccount> = runCatching {
        apiProvider.getAccountApi().registerAccount().body().also {
            authRepository.saveToken(it.token)
        }
    }

    override suspend fun findSelf(): Result<Account> = runCatching {
        apiProvider.getAccountApi().apply {
            setBearerToken(authRepository.getToken() ?: "")
        }.getCurrentAccount().body()
    }

}