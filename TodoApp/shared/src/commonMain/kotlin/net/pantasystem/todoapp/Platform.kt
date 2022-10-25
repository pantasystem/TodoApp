package net.pantasystem.todoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform