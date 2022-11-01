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


import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 *
 * @param id 
 * @param title 
 * @param accountId 
 * @param description 
 * @param createdAt 
 * @param updatedAt 
 * @param completedAt 
 */

data class Task (

    @field:JsonProperty("id")
    val id: kotlin.Long,

    @field:JsonProperty("title")
    val title: kotlin.String,

    @field:JsonProperty("accountId")
    val accountId: kotlin.Long,

    @field:JsonProperty("description")
    val description: kotlin.String? = null,

    @field:JsonProperty("createdAt")
    val createdAt: java.time.OffsetDateTime? = null,

    @field:JsonProperty("updatedAt")
    val updatedAt: java.time.OffsetDateTime? = null,

    @field:JsonProperty("completedAt")
    val completedAt: java.time.OffsetDateTime? = null

)
