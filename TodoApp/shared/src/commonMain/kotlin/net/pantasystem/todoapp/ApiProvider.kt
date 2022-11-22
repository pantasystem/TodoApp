package net.pantasystem.todoapp

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.http.*
import net.pantasystem.todoapp.api.AccountsApi
import net.pantasystem.todoapp.api.TasksApi
import net.pantasystem.todoapp.errors.UnauthorizedError

class ApiProvider {


    private val httpClientConfig: ((HttpClientConfig<*>) -> Unit) = {

        it.defaultRequest {
            this.contentType(ContentType.Application.Json)
        }
        it.HttpResponseValidator {
            validateResponse {
                println("status code:${it.status}")
            }
            handleResponseExceptionWithRequest { exception, request ->
                println("error:$exception")
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
        return TasksApi(baseUrl = BuildKonfig.baseUrl, httpClientConfig = httpClientConfig)
    }

    fun getAccountApi(): AccountsApi {
        return AccountsApi(baseUrl = BuildKonfig.baseUrl, httpClientConfig = httpClientConfig)
    }
}
