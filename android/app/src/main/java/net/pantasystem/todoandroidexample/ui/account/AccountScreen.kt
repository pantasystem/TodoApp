package net.pantasystem.todoandroidexample.ui.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import net.pantasystem.todoandroidexample.ui.MainUiState
import net.pantasystem.todoandroidexample.ui.MainViewModel

@Composable
fun AccountRoute(mainViewModel: MainViewModel) {
    val uiState by mainViewModel.uiState.collectAsState()
    AccountScreen(
        uiState
    )
}

@Composable
fun AccountScreen(
    uiState: MainUiState,
) {
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            when(uiState) {
                is MainUiState.Authorized -> {
                    Text("アカウントId:${uiState.account.id}")
                    Text("Name:${uiState.account.name}")

                }
                is MainUiState.Error -> {}
                MainUiState.Initializing -> {}
            }
        }
    }
}