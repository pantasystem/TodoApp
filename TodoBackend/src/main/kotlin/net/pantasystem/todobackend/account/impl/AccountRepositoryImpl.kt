package net.pantasystem.todobackend.account.impl

import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import net.pantasystem.todobackend.account.AccountRepository

class AccountRepositoryImpl : AccountRepository {

    override fun register(): TokenWithAccount {
        return TokenWithAccount(
            "hog234234",
            account = Account(0, "Pan")
        )
    }
}
