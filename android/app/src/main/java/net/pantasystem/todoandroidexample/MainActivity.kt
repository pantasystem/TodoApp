package net.pantasystem.todoandroidexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import net.pantasystem.todoandroidexample.domain.AccountRepository
import net.pantasystem.todoandroidexample.domain.TaskRepository
import net.pantasystem.todoandroidexample.ui.theme.TodoAndroidExampleTheme
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var accountRepository: AccountRepository

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = null) {
                accountRepository.register().onFailure {
                    Log.e("MainActivity", "register処理失敗", it)
                }.onSuccess {
                    Log.d("MainActivity", "register処理成功:$it")
                }.mapCatching {
                    (0 until 10).map {
                        taskRepository.create(
                            title = "hogehoge:${java.util.Random().nextInt().toString()}",
                        ).getOrThrow()
                    }
                    taskRepository.findTasks().getOrThrow()
                }.onSuccess {
                    Log.d("MainActivity", "タスク作成成功:$it")

                }.onFailure {
                    Log.e("MainActivity", "create task処理失敗", it)
                }
            }
            TodoAndroidExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
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