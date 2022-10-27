package net.pantasystem.todobackend.account

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.AccountsApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account as AccountDTO
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import net.pantasystem.todobackend.auth.Authorize
import net.pantasystem.todobackend.auth.getCurrentAccount
import net.pantasystem.todobackend.auth.getCurrentAccountOrFailure
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder

@RestController
class AccountApiController : AccountsApi {


    @Autowired
    lateinit var accountRepository: AccountRepository

    @Authorize
    override fun getCurrentAccount(): ResponseEntity<AccountDTO> {
        return ResponseEntity.ok(
            RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure().let {
                AccountDTO(it.id.toInt(), it.name)
            }
        )
    }

    override fun registerAccount(): ResponseEntity<TokenWithAccount> {
        val account = accountRepository.register()
        return ResponseEntity(
            TokenWithAccount(
                token = account.token,
                account = AccountDTO(
                    id = account.id.toInt(),
                    name = account.name
                )
            ),
            HttpStatus.OK
        )
    }
}