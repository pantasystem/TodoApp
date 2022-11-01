package net.pantasystem.todoandroidexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.pantasystem.todoandroidexample.domain.AccountRepository
import net.pantasystem.todoandroidexample.domain.TaskRepository
import net.pantasystem.todoandroidexample.ui.App
import net.pantasystem.todoandroidexample.ui.MainViewModel
import net.pantasystem.todoandroidexample.ui.theme.TodoAndroidExampleTheme
import java.util.Random
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoAndroidExampleTheme {
        Greeting("Android")
    }
}