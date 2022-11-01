/**
 * Todo app api
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.0.1
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package net.pantasystem.todoandroidexample.api

import net.pantasystem.todoandroidexample.api.CreateTaskRequest
import net.pantasystem.todoandroidexample.api.Task
import net.pantasystem.todoandroidexample.api.UpdateTaskRequest

import org.openapitools.client.infrastructure.*
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.http.ParametersBuilder
import com.fasterxml.jackson.databind.ObjectMapper

    open class TasksApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    httpClientConfig: ((HttpClientConfig<*>) -> Unit)? = null,
    jsonBlock: ObjectMapper.() -> Unit = ApiClient.JSON_DEFAULT,
    ) : ApiClient(baseUrl, httpClientEngine, httpClientConfig, jsonBlock) {

        /**
        * Complete a Task
        * 
         * @param taskId task id 
         * @return void
        */
        open suspend fun completeTask(taskId: kotlin.Long): HttpResponse<Unit> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.POST,
            "/tasks/{taskId}/complete".replace("{" + "taskId" + "}", "$taskId"),
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Create a Task
        * 
         * @param createTaskRequest  
         * @return Task
        */
            @Suppress("UNCHECKED_CAST")
        open suspend fun createTask(createTaskRequest: CreateTaskRequest): HttpResponse<Task> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = createTaskRequest

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.POST,
            "/tasks",
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Delete Task
        * 
         * @param taskId task id 
         * @return void
        */
        open suspend fun deleteTask(taskId: kotlin.Long): HttpResponse<Unit> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.DELETE,
            "/tasks/{taskId}".replace("{" + "taskId" + "}", "$taskId"),
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Get a Task by TaskId
        * 
         * @param taskId task id 
         * @return Task
        */
            @Suppress("UNCHECKED_CAST")
        open suspend fun getTask(taskId: kotlin.Long): HttpResponse<Task> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/tasks/{taskId}".replace("{" + "taskId" + "}", "$taskId"),
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Get all tasks
        * 自身が保有しているタスクを取得する
         * @return kotlin.collections.List<Task>
        */
            @Suppress("UNCHECKED_CAST")
        open suspend fun getTasks(): HttpResponse<kotlin.collections.List<Task>> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/tasks",
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Un Complete a Task
        * 
         * @param taskId task id 
         * @return void
        */
        open suspend fun uncompleteTask(taskId: kotlin.Long): HttpResponse<Unit> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.POST,
            "/tasks/{taskId}/uncomplete".replace("{" + "taskId" + "}", "$taskId"),
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        /**
        * Update Task
        * 
         * @param taskId task id 
         * @param updateTaskRequest  
         * @return void
        */
        open suspend fun updateTask(taskId: kotlin.Long, updateTaskRequest: UpdateTaskRequest): HttpResponse<Unit> {

            val localVariableAuthNames = listOf<String>("ApiKeyAuth")

            val localVariableBody = updateTaskRequest

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.PUT,
            "/tasks/{taskId}".replace("{" + "taskId" + "}", "$taskId"),
            query = localVariableQuery,
            headers = localVariableHeaders
            )

            return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        }
