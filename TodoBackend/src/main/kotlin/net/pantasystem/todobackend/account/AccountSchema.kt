package net.pantasystem.todobackend.account

import org.jetbrains.exposed.sql.Table

object Accounts : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", length = 256)
    val token = varchar("token", length = 256).index().uniqueIndex()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}