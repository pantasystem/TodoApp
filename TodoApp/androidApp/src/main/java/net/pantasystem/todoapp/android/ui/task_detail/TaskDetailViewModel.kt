package net.pantasystem.todoapp.android.ui.task_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.domain.LoadOneTaskUseCase
import net.pantasystem.todoapp.repository.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val loadOneTaskUseCase: LoadOneTaskUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskArgs = TaskArgs(savedStateHandle)

    private val _task = MutableStateFlow<Task?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<Throwable?>(null)

    val uiState = combine(_task, _isLoading, _error) { task, loading, e ->
        TaskDetailUiState(task, loading, e)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TaskDetailUiState())

    fun load() {
        viewModelScope.launch {
            _isLoading.value = true
            loadOneTaskUseCase(
                taskArgs.taskId
            ).onSuccess {
                _error.value = null
                _task.value = it
            }.onFailure {
                _error.value = it
            }
            _isLoading.value = false
        }
    }
}

data class TaskDetailUiState(
    val task: Task? = null,
    val isLoading: Boolean = true,
    val error: Throwable? = null
)