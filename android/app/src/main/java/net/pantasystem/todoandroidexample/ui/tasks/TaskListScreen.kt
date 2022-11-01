package net.pantasystem.todoandroidexample.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.pantasystem.todoandroidexample.api.Task

@Composable
fun TaskListRoute(
    modifier: Modifier = Modifier,
    navigateToTaskDetail: (Long) -> Unit,
    navigateToTaskEditor: () -> Unit,
    taskListViewModel: TaskListViewModel = hiltViewModel()
) {
    val uiState by taskListViewModel.uiState.collectAsState()

    LaunchedEffect(null) {
        taskListViewModel.loadTasks()
    }

    TaskListScreen(
        modifier = modifier,
        uiState = uiState,
        onSearchButtonClicked = {
            taskListViewModel.toggleSearchMode()
        },
        onSearchWordChanged = {
            taskListViewModel.search(it)
        },
        onCreateTaskFabClicked = {
            navigateToTaskEditor()
        },
        onAction = {
            when (it) {
                is TaskListScreenAction.OnCompleteCheckboxToggled -> {
                    taskListViewModel.toggleComplete(it.task)
                }
                is TaskListScreenAction.OnTaskCardClicked -> {
                    navigateToTaskDetail(it.task.id)
                }
                is TaskListScreenAction.OnCreateTaskFabClicked -> {
                    navigateToTaskEditor()
                }
            }
        },
    )
}

@Composable
fun TaskListScreen(
    modifier: Modifier,
    uiState: TasksUiState,
    onAction: (TaskListScreenAction) -> Unit,
    onSearchButtonClicked: () -> Unit,
    onSearchWordChanged: (String) -> Unit,
    onCreateTaskFabClicked: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TaskListTopAppBar(
                uiState = uiState,
                onSearchWordChanged = onSearchWordChanged,
                onSearchButtonClicked = onSearchButtonClicked
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateTaskFabClicked) {
                Icon(Icons.Default.Add, null)
            }
        }

    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(uiState.filteredTasks, key = { it.id }) { task ->
                TaskCard(
                    task = task,
                    onClick = {
                        onAction(TaskListScreenAction.OnTaskCardClicked(task))
                    },
                    onToggle = {
                        onAction(TaskListScreenAction.OnCompleteCheckboxToggled(task, it))
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(task: Task, onClick: () -> Unit, onToggle: (Boolean) -> Unit) {
    Surface(
        onClick = {
            onClick()
        },
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(1f)

            ) {
                Text(task.title, fontWeight = FontWeight.ExtraBold)
                Text(task.title)
            }

            CircleCheckbox(selected = task.completedAt != null, onChecked = {
                onToggle(task.completedAt != null)
            })

        }
    }
}

@Composable
@Stable
private fun CircleCheckbox(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    onChecked: () -> Unit
) {

    val color = MaterialTheme.colors
    val imageVector = if (selected) Icons.Filled.CheckCircle else Icons.Outlined.Circle
    val tint = color.primary
    val background = if (selected) Color.White else Color.Transparent

    IconButton(
        onClick = { onChecked() },
        modifier = modifier,
        enabled = enabled
    ) {

        Icon(
            imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}

@Composable
fun TaskListTopAppBar(
    uiState: TasksUiState,
    onSearchWordChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit
) {
    var searchWord: String by remember {
        mutableStateOf("")
    }

    LaunchedEffect(searchWord) {
        onSearchWordChanged(searchWord)
    }
    when (uiState.searchWord) {
        SearchWord.None -> {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                title = {
                    Text("Todo一覧")
                },
                actions = {
                    IconButton(onClick = onSearchButtonClicked) {
                        Icon(Icons.Default.Search, null)
                    }
                }
            )
        }
        is SearchWord.Search -> {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                OutlinedTextField(
                    value = searchWord,
                    onValueChange = {
                        searchWord = it
                    },
                    modifier = Modifier
                        .weight(1f)
                )
                IconButton(onClick = onSearchButtonClicked) {
                    Icon(Icons.Default.Close, null)
                }
            }
        }
    }
}

sealed interface TaskListScreenAction {
    data class OnTaskCardClicked(val task: Task) : TaskListScreenAction
    data class OnCompleteCheckboxToggled(val task: Task, val nextValue: Boolean) :
        TaskListScreenAction

    object OnCreateTaskFabClicked : TaskListScreenAction
}