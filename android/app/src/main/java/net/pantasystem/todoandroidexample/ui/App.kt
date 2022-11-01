package net.pantasystem.todoandroidexample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.pantasystem.todoandroidexample.navigation.Routes
import net.pantasystem.todoandroidexample.ui.account.AccountRoute
import net.pantasystem.todoandroidexample.ui.task_detail.navigateTaskDetail
import net.pantasystem.todoandroidexample.ui.task_detail.taskDetailNavGraph
import net.pantasystem.todoandroidexample.ui.task_editor.taskEditorNavGraph
import net.pantasystem.todoandroidexample.ui.tasks.TaskListRoute

val items = listOf(
    Routes.TopLevel.Home,
    Routes.TopLevel.Account,
)

@Composable
fun App(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val currentUiState by mainViewModel.uiState.collectAsState()
    LaunchedEffect(null) {
        mainViewModel.loadInit()
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {

        },
        bottomBar = {
            if (currentUiState is MainUiState.Authorized) {
                BottomNav(navController = navController)
            }
        },
    ) {
        when (currentUiState) {
            is MainUiState.Authorized -> {
                NavHost(
                    navController = navController,
                    startDestination = Routes.TopLevel.Home.route
                ) {
                    composable(Routes.TopLevel.Home.route) {
                        TaskListRoute(
                            navigateToTaskDetail = {
                                navController.navigateTaskDetail(it)
                            },
                            navigateToTaskEditor = {

                            }
                        )
                    }
                    composable(Routes.TopLevel.Account.route) {
                        AccountRoute()
                    }
                    taskDetailNavGraph(onNavigateUp = {
                        navController.popBackStack()
                    })
                    taskEditorNavGraph()
                }
            }
            is MainUiState.Error -> {
                Text("Error")
            }
            MainUiState.Initializing -> {
                InitialScreen()
            }
        }
    }


}

@Composable
private fun InitialScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun BottomNav(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(screen.label)
                },
                icon = {
                    Icon(screen.icon, null)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
            )
        }
    }
}

