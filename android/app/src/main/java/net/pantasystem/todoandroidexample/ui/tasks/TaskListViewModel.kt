package net.pantasystem.todoandroidexample.ui.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.pantasystem.todoandroidexample.api.Task
import net.pantasystem.todoandroidexample.domain.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    private val _errors = MutableSharedFlow<Throwable?>()
    val errors: SharedFlow<Throwable?> = _errors

    private val _searchWord = MutableStateFlow<SearchWord>(SearchWord.None)

    val uiState = combine(_tasks, _searchWord) { tasks, searchWord ->
        TasksUiState(
            tasks,
            searchWord,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TasksUiState())

    init {
        viewModelScope.launch {
            errors.collect {
                Log.e("TaskListViewModel", "error", it)
            }
        }
    }
    fun loadTasks() {
        viewModelScope.launch {
            taskRepository.findTasks().onSuccess {
                _tasks.value = it
            }.onFailure {
                _errors.tryEmit(it)
            }
        }
    }

    fun toggleComplete(task: Task) {
        viewModelScope.launch {
            if (task.completedAt == null) {
                taskRepository.completeTask(task.id)
            } else {
                taskRepository.uncompleteTask(task.id)
            }.onSuccess {
                loadTasks()
            }.onFailure {
                _errors.tryEmit(it)
            }
        }
    }

    fun toggleSearchMode() {
        _searchWord.update {
            when(it) {
                SearchWord.None -> SearchWord.Search("")
                is SearchWord.Search -> SearchWord.None
            }
        }
    }

    fun search(keyword: String) {
        _searchWord.update {
            when(it) {
                SearchWord.None -> SearchWord.None
                is SearchWord.Search -> SearchWord.Search(keyword)
            }
        }
    }

}

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val searchWord: SearchWord = SearchWord.None,
) {
    val filteredTasks: List<Task> by lazy {
        when(searchWord) {
            SearchWord.None -> tasks
            is SearchWord.Search -> {
                tasks.filter {
                    it.title.startsWith(searchWord.keyword)
                }
            }
        }
    }
}

sealed interface SearchWord {
    object None : SearchWord
    data class Search(val keyword: String) : SearchWord
}