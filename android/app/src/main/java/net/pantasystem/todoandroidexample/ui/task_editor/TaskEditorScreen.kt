package net.pantasystem.todoandroidexample.ui.task_editor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val taskEditorRoute = "/task-editor"
fun NavGraphBuilder.taskEditorNavGraph(onNavigateUp: () -> Unit) {
    composable(taskEditorRoute) {
        TaskEditorRoute(onNavigateUp = onNavigateUp)
    }
}

fun NavController.navigateToTaskEditor(taskId: Long? = null) {
    navigate(taskEditorRoute)
}

@Composable
fun TaskEditorRoute(
    modifier: Modifier = Modifier,
    taskEditorViewModel: TaskEditorViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    TaskEditorScreen(modifier, taskEditorViewModel, onNavigateUp = onNavigateUp)
}

@Composable
fun TaskEditorScreen(
    modifier: Modifier,
    taskEditorViewModel: TaskEditorViewModel,
    onNavigateUp: () -> Unit,
) {

    val uiState by taskEditorViewModel.uiState.collectAsState()

    val saveState by taskEditorViewModel.saveResult.collectAsState()

    LaunchedEffect(saveState) {
        if (saveState?.isSuccess == true) {
            onNavigateUp()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("タスクを作成")
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = taskEditorViewModel.title,
                onValueChange = taskEditorViewModel::onTitleChanged,
                label = {
                    Text("タスク名")
                },
                isError = uiState.validationResult.titleErrorMessage != null,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )
            if (uiState.validationResult.titleErrorMessage != null) {
                Text(
                    uiState.validationResult.titleErrorMessage ?: "",
                    color = MaterialTheme.colors.error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = taskEditorViewModel.description,
                onValueChange = taskEditorViewModel::onDescriptionChanged,
                label = {
                    Text("詳細")
                },
                isError = uiState.validationResult.titleErrorMessage != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (uiState.validationResult.descriptionErrorMessage != null) {
                Text(
                    uiState.validationResult.descriptionErrorMessage ?: "",
                    color = MaterialTheme.colors.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    taskEditorViewModel.onSave()
                },
                enabled = uiState.validationResult.isOk
            ) {
                Text("保存")
            }

        }
    }
}