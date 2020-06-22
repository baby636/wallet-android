/**
 * API
 * Create API keys in your profile and use public API key as username and secret as password to authorize.
 *
 * The version of the OpenAPI document: 2.19.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.mycelium.bequant.remote.trading.model

import com.squareup.moshi.Json

/**
 *
 * @param main
 * @param trading
 */

data class SubAccountBalance(
        @Json(name = "main")
        val main: kotlin.Array<SubAccountBalanceMain>? = null,
        @Json(name = "trading")
        val trading: kotlin.Array<SubAccountBalanceMain>? = null
)

