package net.pantasystem.todobackend.account

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.AccountsApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account as AccountDTO
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountApiController : AccountsApi {


    @Autowired
    lateinit var accountRepository: AccountRepository

    override fun getCurrentAccount(): ResponseEntity<AccountDTO> {
        return super.getCurrentAccount()
    }

    override fun registerAccount(): ResponseEntity<TokenWithAccount> {
        return ResponseEntity(
            accountRepository.register(),
            HttpStatus.OK
        )
    }
}