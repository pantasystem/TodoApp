package net.pantasystem.todoapp.android.ui.task_editor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.pantasystem.todoapp.api.Task
import net.pantasystem.todoapp.domain.CreateTask
import net.pantasystem.todoapp.domain.CreateTaskUseCase
import net.pantasystem.todoapp.domain.asResult
import javax.inject.Inject

@HiltViewModel
class TaskEditorViewModel @Inject constructor(
    val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _saveResult = MutableStateFlow<Result<List<Task>>?>(null)
    val saveResult: StateFlow<Result<List<Task>>?> = _saveResult


    private val formState = combine(
        snapshotFlow {
            title
        },
        snapshotFlow {
            description
        }
    ) { title, description ->
        TaskEditorFormState(title = title, description = description)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TaskEditorFormState())

    private val validationResult = formState.map {
        EditTaskValidationResult.create(it.title, it.description)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), EditTaskValidationResult())

    val uiState = combine(formState, validationResult) { formState, validation ->
        TaskEditorUiState(
            formState = formState,
            validationResult = validation
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TaskEditorUiState())


    fun onTitleChanged(value: String) {
        title = value
    }


    fun onDescriptionChanged(value: String) {
        description = value
    }

    fun onSave() {
        viewModelScope.launch {
            if (EditTaskValidationResult.create(title, description).isOk) {
                val saveResult = createTaskUseCase.asResult(
                    CreateTask(
                        title = title,
                        description = description,
                    )
                )
                _saveResult.value = saveResult
            }
        }
    }
}

data class TaskEditorFormState(
    val title: String = "",
    val description: String = "",
)

data class TaskEditorUiState(
    val formState: TaskEditorFormState = TaskEditorFormState(),
    val validationResult: EditTaskValidationResult = EditTaskValidationResult(),
)

data class EditTaskValidationResult(
    val titleErrorMessage: String? = null,
    val descriptionErrorMessage: String? = null,
) {
    val isOk: Boolean = titleErrorMessage == null && descriptionErrorMessage == null

    companion object {
        fun create(title: String, description: String): EditTaskValidationResult {
            return EditTaskValidationResult(
                titleErrorMessage = if (title.isEmpty()) {
                    "1文字以上30文字未満の範囲で入力してください"
                } else if (title.codePointCount(0, title.length) > 30) {
                    "30文字以上入力できません"
                } else {
                    null
                },
                descriptionErrorMessage = if (description.codePointCount(
                        0,
                        description.length
                    ) > 256
                ) {
                    "255文字未満にしてください"
                } else {
                    null
                }
            )
        }
    }
}