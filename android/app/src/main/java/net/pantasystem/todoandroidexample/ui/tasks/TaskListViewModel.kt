package net.pantasystem.todoandroidexample.ui.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.pantasystem.todoandroidexample.api.Task
import net.pantasystem.todoandroidexample.domain.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _errors = MutableSharedFlow<Throwable?>()
    val errors: SharedFlow<Throwable?> = _errors

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


}