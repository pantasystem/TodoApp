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

import net.pantasystem.todoandroidexample.api.Account

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 *
 * @param token 
 * @param account 
 */

data class TokenWithAccount (

    @field:JsonProperty("token")
    val token: kotlin.String,

    @field:JsonProperty("account")
    val account: Account

)

