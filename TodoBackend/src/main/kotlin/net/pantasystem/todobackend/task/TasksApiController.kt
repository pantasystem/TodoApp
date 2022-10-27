package net.pantasystem.todobackend.task

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
import net.pantasystem.todobackend.auth.Authorize
import net.pantasystem.todobackend.auth.getCurrentAccount
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Security
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task as TaskDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.support.RequestContext

@RestController
class TaskApiController : TasksApi {
    @Authorize
    override fun getTasks(): ResponseEntity<List<TaskDTO>> {
        val token = RequestContextHolder.getRequestAttributes()?.getCurrentAccount()
        println(token)


        return ResponseEntity.ok(listOf())
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

    override fun getTask(taskId: Int): ResponseEntity<TaskDTO> {
        return super.getTask(taskId)
    }
}