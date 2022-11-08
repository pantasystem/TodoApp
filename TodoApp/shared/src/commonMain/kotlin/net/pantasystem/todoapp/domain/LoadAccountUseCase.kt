package net.pantasystem.todoapp.domain

import net.pantasystem.todoapp.api.Account
import net.pantasystem.todoapp.errors.UnauthorizedError
import net.pantasystem.todoapp.repository.AccountRepository

class LoadAccountUseCase(
    val accountRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<Account> = runCatching {
        try {
            accountRepository.findSelf().getOrThrow()
        } catch (e: UnauthorizedError) {
            accountRepository.register().getOrThrow().account
        }

    }
}