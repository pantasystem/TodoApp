package net.pantasystem.todobackend.task

import net.pantasystem.todobackend.account.Accounts
import net.pantasystem.todobackend.db_common.datetimeWithTZ
import org.jetbrains.exposed.sql.Table

object Tasks : Table() {
    val id = long("id").autoIncrement()
    val title = text("title")
    val description = text("description")
    val createdAt = datetimeWithTZ("createdAt")
    val updatedAt = datetimeWithTZ("updatedAt")
    val accountId = (long("accountId") references Accounts.id).index()
    val completedAt = datetimeWithTZ("completedAt")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}