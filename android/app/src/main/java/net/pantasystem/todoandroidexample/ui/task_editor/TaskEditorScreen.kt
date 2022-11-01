package net.pantasystem.todoandroidexample.ui.task_editor

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import net.pantasystem.todoandroidexample.navigation.Routes

fun NavGraphBuilder.taskEditorNavGraph() {
    composable(Routes.TaskEditor.route) {
        TaskEditorRoute()
    }
}
@Composable
fun TaskEditorRoute() {
    TaskEditorScreen()
}

@Composable
fun TaskEditorScreen() {

}