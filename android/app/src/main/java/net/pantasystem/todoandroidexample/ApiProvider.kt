package net.pantasystem.todoandroidexample

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.jackson.*
import io.ktor.util.pipeline.*
import net.pantasystem.todoandroidexample.api.AccountsApi
import net.pantasystem.todoandroidexample.api.TasksApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {

    val client = HttpClient(CIO)
    fun getTasksApi(): TasksApi {
        return TasksApi(baseUrl = "http://10.200.1.181:8080",  httpClientConfig = {
            it.install(ContentNegotiation) {
                jackson()
            }
        }).apply {

        }
    }

    fun getAccountApi(): AccountsApi {
        return AccountsApi(baseUrl = "http://10.200.1.181:8080", httpClientConfig = {
            it.install(ContentNegotiation) {
                jackson()
            }
        }).apply {

        }
    }
}
