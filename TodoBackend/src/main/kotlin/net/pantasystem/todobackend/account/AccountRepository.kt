package net.pantasystem.todobackend.account

import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository {
    fun register(): TokenWithAccount
}