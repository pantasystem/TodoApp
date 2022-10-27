package net.pantasystem.todobackend.auth

import net.pantasystem.todobackend.account.Account
import net.pantasystem.todobackend.account.Accounts
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.server.ResponseStatusException

fun RequestAttributes.getCurrentAccount(): Account? {
    val token = (this as? ServletRequestAttributes)
        ?.request
        ?.getHeader("Authorization")
        ?.getBearerToken()
    if (token.isNullOrBlank()) {
        return null
    }
    return transaction {
        Accounts.select(Accounts.token eq token).limit(1).map {
            Account(
                id = it[Accounts.id],
                token = it[Accounts.token],
                name = it[Accounts.name]
            )
        }.firstOrNull()
    }
}

fun RequestAttributes.getCurrentAccountOrFailure(): Account {
    return getCurrentAccount() ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
}