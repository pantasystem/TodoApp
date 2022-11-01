package net.pantasystem.todobackend.task

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.TasksApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.CreateTaskRequest
import kotlinx.datetime.Clock
import net.pantasystem.todobackend.auth.Authorize
import net.pantasystem.todobackend.auth.getCurrentAccount
import net.pantasystem.todobackend.auth.getCurrentAccountOrFailure
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Security
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Task as TaskDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.support.RequestContext
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

@RestController
class TaskApiController : TasksApi {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Authorize
    override fun getTasks(): ResponseEntity<List<TaskDTO>> {
        val account = RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure()

        return ResponseEntity.ok(taskRepository.findByAccount(account.id).map {
            TaskDTO(
                id = it.id,
                title = it.title,
                description = it.description,
                accountId = it.accountId,
                completedAt = it.completedAt?.atOffset(ZoneOffset.UTC),
                updatedAt = it.updatedAt?.atOffset(ZoneOffset.UTC),
                createdAt = it.createdAt?.atOffset(ZoneOffset.UTC),
            )
        })
    }

    @Authorize
    override fun completeTask(taskId: Long): ResponseEntity<Unit> {
        val account = RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure()
        val task = taskRepository.findOne(taskId.toLong())

        if (account.id != task.accountId) {
            return ResponseEntity
                .notFound()
                .build()
        }

        taskRepository.update(task.copy(completedAt = Instant.now()))
        return ResponseEntity.ok(Unit)
    }
    @Authorize
    override fun createTask(createTaskRequest: CreateTaskRequest): ResponseEntity<Unit> {
        val account = RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure()
        taskRepository.create(Task(
            title = createTaskRequest.title,
            description = createTaskRequest.description,
            accountId = account.id
        ))
        return ResponseEntity.ok(Unit)
    }

    @Authorize

    override fun deleteTask(taskId: Long): ResponseEntity<Unit> {
        val account = RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure()


        return super.deleteTask(taskId)
    }

    @Authorize
    override fun getTask(taskId: Long): ResponseEntity<TaskDTO> {
        val account = RequestContextHolder.getRequestAttributes().getCurrentAccountOrFailure()
        val task = taskRepository.findOne(taskId.toLong())
        if (account.id != task.accountId) {
            return ResponseEntity
                .notFound()
                .build()
        }
        return ResponseEntity.ok(TaskDTO(
            id = task.id,
            title = task.title,
            description = task.description,
            accountId = account.id,
            completedAt = task.completedAt?.atOffset(ZoneOffset.UTC),
            updatedAt = task.updatedAt?.atOffset(ZoneOffset.UTC),
            createdAt = task.createdAt?.atOffset(ZoneOffset.UTC),
        ))
    }
}