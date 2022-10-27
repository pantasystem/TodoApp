package net.pantasystem.todobackend.task

import net.pantasystem.todobackend.account.Accounts
import net.pantasystem.todobackend.db_common.datetimeWithTZ
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object Tasks : Table() {
    val id = long("id").autoIncrement()
    val title = text("title")
    val description = text("description")
    val createdAt = timestamp("createdAt")
    val updatedAt = timestamp("updatedAt")
    val accountId = (long("accountId") references Accounts.id).index()
    val completedAt = timestamp("completedAt").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}