package net.pantasystem.todobackend.task.impl

import net.pantasystem.todobackend.task.Task
import net.pantasystem.todobackend.task.TaskRepository
import net.pantasystem.todobackend.task.Tasks
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.Instant

class TaskRepositoryImpl : TaskRepository {

    override fun create(task: Task): Task {
        val tId = transaction {
            Tasks.insert {
                it[title] = task.title
                it[description] = task.description ?: ""
                it[createdAt] = Instant.now()
                it[updatedAt] = Instant.now()
                it[accountId] = task.accountId
            }[Tasks.id]
        }
        return findOne(tId)
    }

    override fun findByAccount(accountId: Long): List<Task> {
        return transaction {
            Tasks.select(Tasks.accountId eq accountId).orderBy(Tasks.createdAt).map {
                Task(
                    id = it[Tasks.id],
                    title = it[Tasks.title],
                    description = it[Tasks.description],
                    completedAt = it[Tasks.completedAt],
                    updatedAt = it[Tasks.updatedAt],
                    createdAt = it[Tasks.createdAt],
                    accountId = it[Tasks.accountId],
                )
            }
        }
    }

    override fun findOne(taskId: Long): Task {
        return transaction {
            Tasks.select(Tasks.id eq taskId).map {
                Task(
                    id = it[Tasks.id],
                    title = it[Tasks.title],
                    description = it[Tasks.description],
                    completedAt = it[Tasks.completedAt],
                    updatedAt = it[Tasks.updatedAt],
                    createdAt = it[Tasks.createdAt],
                    accountId = it[Tasks.accountId],
                )
            }.first()
        }
    }

    override fun update(task: Task) {
        transaction {
            Tasks.update({
                Tasks.id eq task.id
            }) {
                it[title] = task.title
                it[description] = task.description ?: ""
                it[completedAt] = task.completedAt
                it[updatedAt] = Instant.now()
            }
        }
    }
}