package net.pantasystem.todobackend.auth


fun String.getBearerToken(): String? {
    if (!this.startsWith("Bearer ")) {
        return null
    }
    return this.substring(7)
}