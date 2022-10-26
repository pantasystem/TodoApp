package net.pantasystem.todobackend

//import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.AccountsApi
//import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
//import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account
//import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
//import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task
//import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.AccountsApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TodoBackendApplication

fun main(args: Array<String>) {
    runApplication<TodoBackendApplication>(*args)
}

@RestController
class TestController {

    @RequestMapping("test", method = [RequestMethod.GET])
    fun test(): String {

        return "Hello world"
    }
}

@RestController
class TaskApiController : TasksApi {
    override fun getTasks(): ResponseEntity<List<Task>> {
        return super.getTasks()
    }

    override fun completeTask(taskId: Int): ResponseEntity<Unit> {
        return super.completeTask(taskId)
    }

    override fun createTask(createTaskRequest: CreateTaskRequest): ResponseEntity<Unit> {
        return super.createTask(createTaskRequest)
    }

    override fun deleteTask(taskId: Int): ResponseEntity<Unit> {
        return super.deleteTask(taskId)
    }

    override fun getTask(taskId: Int): ResponseEntity<Task> {
        return super.getTask(taskId)
    }
}
@RestController
class AccountApiController : AccountsApi {


    @Autowired lateinit var accountRepository: AccountRepository

    override fun getCurrentAccount(): ResponseEntity<Account> {
        return super.getCurrentAccount()
    }

    override fun registerAccount(): ResponseEntity<TokenWithAccount> {
        return ResponseEntity(
            accountRepository.register(),
            HttpStatus.OK
        )
    }
}
@Repository
interface AccountRepository {
    fun register(): TokenWithAccount
}
class AccountRepositoryImpl : AccountRepository{

    override fun register(): TokenWithAccount {
        return TokenWithAccount(
            "hog234234",
            account = Account(0, "Pan")
        )
    }
}

@Configuration
class RepoConfig {
    @Bean
    fun provideAccountRepository(): AccountRepository {
        return AccountRepositoryImpl()
    }
}