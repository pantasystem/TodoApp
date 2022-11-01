package net.pantasystem.todoandroidexample.impl

import net.pantasystem.todoandroidexample.ApiProvider
import net.pantasystem.todoandroidexample.api.Account
import net.pantasystem.todoandroidexample.api.TokenWithAccount
import net.pantasystem.todoandroidexample.domain.AccountRepository
import net.pantasystem.todoandroidexample.domain.AuthRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
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
            setAccessToken(authRepository.getToken() ?: "")
        }.getCurrentAccount().body()
    }

}