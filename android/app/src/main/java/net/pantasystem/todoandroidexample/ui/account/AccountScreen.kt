package net.pantasystem.todoandroidexample.ui.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import net.pantasystem.todoandroidexample.ui.MainUiState
import net.pantasystem.todoandroidexample.ui.MainViewModel

@Composable
fun AccountRoute(mainViewModel: MainViewModel = hiltViewModel()) {
    val uiState by mainViewModel.uiState.collectAsState()
    AccountScreen(
        uiState
    )
}

@Composable
fun AccountScreen(
    uiState: MainUiState
) {
    LazyColumn(Modifier.fillMaxSize()) {
        item {

        }
    }
}