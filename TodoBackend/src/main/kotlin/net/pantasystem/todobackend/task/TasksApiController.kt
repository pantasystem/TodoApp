package net.pantasystem.todobackend.task

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task as TaskDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskApiController : TasksApi {
    override fun getTasks(): ResponseEntity<List<TaskDTO>> {
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

    override fun getTask(taskId: Int): ResponseEntity<TaskDTO> {
        return super.getTask(taskId)
    }
}