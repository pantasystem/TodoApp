package net.pantasystem.todobackend


import net.pantasystem.todobackend.account.Accounts
import net.pantasystem.todobackend.task.Tasks
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TodoBackendApplication

fun main(args: Array<String>) {
    Database.connect("jdbc:postgresql://psql:5432/todo_db", "org.postgresql.Driver", "dbuser", "secret")
    transaction {
        SchemaUtils.create(Accounts, Tasks)
    }
    runApplication<TodoBackendApplication>(*args)
}

@RestController
class TestController {

    @RequestMapping("test", method = [RequestMethod.GET])
    fun test(): String {

        return "Hello world"
    }
}





