package net.pantasystem.todobackend.account.impl

import net.pantasystem.todobackend.account.Account
import net.pantasystem.todobackend.account.AccountRepository
import net.pantasystem.todobackend.account.Accounts
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class AccountRepositoryImpl : AccountRepository {

    override fun register(): Account {
        val account = Account(
            name = "",
            token = UUID.randomUUID().toString()
        )
        val aId = transaction {
            Accounts.insert {
                it[name] = account.name
                it[token] = account.token
            }[Accounts.id]
        }
        return account.copy(id = aId)
    }
}
