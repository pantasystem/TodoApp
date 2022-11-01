package net.pantasystem.todoandroidexample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.pantasystem.todoandroidexample.ui.account.AccountRoute
import net.pantasystem.todoandroidexample.ui.tasks.TaskListRoute

val items = listOf(
    TopLevelScreen.Home,
    TopLevelScreen.Account,
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
        },
    ) {
        when(currentUiState) {
            is MainUiState.Authorized -> {
                NavHost(navController = navController, startDestination = "/home") {
                    composable("/home") {
                        TaskListRoute()
                    }
                    composable("/account") {
                        AccountRoute()
                    }
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

sealed class TopLevelScreen(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {

    object Home : TopLevelScreen(
        label = "Home",
        icon = Icons.Default.Home,
        route = "/home"
    )
    object Account : TopLevelScreen(
        label = "Account",
        icon = Icons.Default.AccountCircle,
        route = "/account"
    )
}