package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Account
import net.pantasystem.todoapp.errors.UnauthorizedError
import net.pantasystem.todoapp.repository.AccountRepository

class LoadAccountUseCase(
    val accountRepository: AccountRepository
) : UseCase0Returns<Account> {


    override suspend fun invoke(): Account {
        return runCatching {
            try {
                accountRepository.findSelf().getOrThrow()
            } catch (e: Exception) {
                accountRepository.register().getOrThrow().account
            }
        }.getOrThrow()
    }
}