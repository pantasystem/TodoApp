package net.pantasystem.todoandroidexample.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes (
    val route: String
) {

    sealed class TopLevel(
        route: String,
        val label: String,
        val icon: ImageVector,
    ) : Routes(route = route) {
        object Home : TopLevel(
            label = "Home",
            icon = Icons.Default.Home,
            route = "/home"
        )
        object Account : TopLevel(
            label = "Account",
            icon = Icons.Default.AccountCircle,
            route = "/account"
        )
    }

    object TaskDetail : Routes(
        "/tasks/{taskId}"
    )

    object TaskEditor : Routes(
        "/task-editor"
    )
}
