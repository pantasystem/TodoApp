package net.pantasystem.todoandroidexample.ui.task_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import net.pantasystem.todoandroidexample.navigation.Routes


fun NavController.navigateTaskDetail(taskId: Long) {

}

fun NavGraphBuilder.taskDetailNavigation() {
    composable(Routes.TaskDetail.route) {
        TaskDetailRoute()
    }
}

@Composable
fun TaskDetailRoute() {

}

@Composable
fun TaskDetailScreen() {

}