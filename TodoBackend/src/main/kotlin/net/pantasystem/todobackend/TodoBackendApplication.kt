package net.pantasystem.todobackend


import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.AccountsApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Account
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.TokenWithAccount
import net.pantasystem.todobackend.account.AccountRepository
import net.pantasystem.todobackend.account.impl.AccountRepositoryImpl
import org.jetbrains.exposed.sql.Database
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
    Database.connect("jdbc:postgresql://psql:5432/todo_db", "org.postgresql.Driver", "dbuser", "secret")
    runApplication<TodoBackendApplication>(*args)
}

@RestController
class TestController {

    @RequestMapping("test", method = [RequestMethod.GET])
    fun test(): String {

        return "Hello world"
    }
}





