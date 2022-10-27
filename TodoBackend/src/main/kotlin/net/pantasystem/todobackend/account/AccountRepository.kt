package net.pantasystem.todobackend.account

import org.springframework.stereotype.Repository

@Repository
interface AccountRepository {
    fun register(): Account
}