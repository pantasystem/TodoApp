package net.pantasystem.todoandroidexample

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.util.pipeline.*
import net.pantasystem.todoandroidexample.api.AccountsApi
import net.pantasystem.todoandroidexample.api.TasksApi
import net.pantasystem.todoandroidexample.errors.UnauthorizedError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {

    val client = HttpClient(CIO)

    private val httpClientConfig: ((HttpClientConfig<*>) -> Unit) = {

        it.defaultRequest {
            this.contentType(ContentType.Application.Json)
        }
        it.HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response
                if (exceptionResponse.status == HttpStatusCode.Unauthorized) {
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    throw UnauthorizedError(exceptionResponseText)
                }
            }
        }


    }
    fun getTasksApi(): TasksApi {
        return TasksApi(baseUrl = BuildConfig.BASE_URL, httpClientConfig = httpClientConfig).apply {

        }
    }

    fun getAccountApi(): AccountsApi {
        return AccountsApi(baseUrl = BuildConfig.BASE_URL, httpClientConfig = httpClientConfig)
    }
}
