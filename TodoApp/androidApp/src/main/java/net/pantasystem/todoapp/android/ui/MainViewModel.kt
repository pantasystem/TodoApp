package net.pantasystem.todoapp.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.pantasystem.todoapp.api.Account
import net.pantasystem.todoapp.repository.AccountRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Initializing)
    val uiState: StateFlow<MainUiState> = _uiState


    fun loadInit() {
        viewModelScope.launch {
            accountRepository.findSelf().onFailure {
                accountRepository.register().getOrThrow()
            }.onSuccess {
                _uiState.value = MainUiState.Authorized(it)
            }.onFailure {
                _uiState.value = MainUiState.Error(it)
            }
        }
    }
}


sealed interface MainUiState {
    object Initializing : MainUiState
    data class Authorized(val account: Account) : MainUiState
    data class Error(val throwable: Throwable) : MainUiState
}