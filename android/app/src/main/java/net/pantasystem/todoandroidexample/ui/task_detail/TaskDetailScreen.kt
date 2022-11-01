package net.pantasystem.todoandroidexample.ui.task_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val taskDetailRoute: String = "/tasks/{taskId}"
const val taskIdArg: String = "taskId"

class TaskArgs(savedStateHandle: SavedStateHandle) {
    val taskId: Long by lazy {
        savedStateHandle[taskIdArg]!!
    }
}

fun NavController.navigateTaskDetail(taskId: Long) {
    navigate("/tasks/$taskId")
}

fun NavGraphBuilder.taskDetailNavGraph(
    onNavigateUp: () -> Unit,
) {
    composable(
        route = taskDetailRoute,
        arguments = listOf(
            navArgument(taskIdArg) { type = NavType.LongType }
        )
    ) {
        TaskDetailRoute(onNavigateUp = onNavigateUp)
    }
}

@Composable
fun TaskDetailRoute(
    onNavigateUp: () -> Unit,
    taskDetailViewModel: TaskDetailViewModel = hiltViewModel()
) {

    val uiState by taskDetailViewModel.uiState.collectAsState()
    TaskDetailScreen(
        uiState = uiState,
        onLoadTask = {
            taskDetailViewModel.load()
        },
        onNavigateUp = onNavigateUp
    )
}

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onLoadTask: () -> Unit,
    onNavigateUp: () -> Unit,
) {

    LaunchedEffect(true) {
        onLoadTask()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    IconButton(onClick = onNavigateUp){
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                title = {}
            )
        },
    ) {
        LazyColumn(
            Modifier.fillMaxSize().padding(it)
        ) {
            if (uiState.isLoading && uiState.task == null) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else if (uiState.task == null && uiState.error != null) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("読み込みに失敗　")
                    }
                }
            }

            if (uiState.task != null) {
                item {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            uiState.task.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (uiState.task.description != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                uiState.task.description
                            )
                        }

                    }
                }
            }

        }
    }

}