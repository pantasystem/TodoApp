package net.pantasystem.todoapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import net.pantasystem.todoapp.android.ui.App
import net.pantasystem.todoandroidexample.ui.theme.TodoAndroidExampleTheme
import net.pantasystem.todoapp.android.ui.MainViewModel
import net.pantasystem.todoapp.repository.AccountRepository
import net.pantasystem.todoapp.repository.TaskRepository
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var accountRepository: AccountRepository

    @Inject
    lateinit var taskRepository: TaskRepository

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TodoAndroidExampleTheme {
                // A surface container using the 'background' color from the theme
                App(mainViewModel)
            }
        }
    }
}

