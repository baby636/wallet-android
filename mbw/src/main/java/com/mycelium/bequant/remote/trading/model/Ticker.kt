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
 * @param symbol
 * @param ask Best ASK.
 * @param bid Best BID.
 * @param last Last trade price
 * @param low Min trade price of the last 24 hours.
 * @param high Max trade price of the last 24 hours.
 * @param open Trade price 24 hours ago.
 * @param volume Trading volume in commoduty currency of the last 24 hours.
 * @param volumeQuoute Trading volume in currency of the last 24 hours.
 * @param timestamp Actual timestamp.
 */

data class Ticker(
        @Json(name = "symbol")
        val symbol: kotlin.String? = null,
        /* Best ASK. */
        @Json(name = "ask")
        val ask: kotlin.String? = null,
        /* Best BID. */
        @Json(name = "bid")
        val bid: kotlin.String? = null,
        /* Last trade price */
        @Json(name = "last")
        val last: kotlin.String? = null,
        /* Min trade price of the last 24 hours. */
        @Json(name = "low")
        val low: kotlin.String? = null,
        /* Max trade price of the last 24 hours. */
        @Json(name = "high")
        val high: kotlin.String? = null,
        /* Trade price 24 hours ago. */
        @Json(name = "open")
        val open: kotlin.String? = null,
        /* Trading volume in commoduty currency of the last 24 hours. */
        @Json(name = "volume")
        val volume: kotlin.String? = null,
        /* Trading volume in currency of the last 24 hours. */
        @Json(name = "volumeQuoute")
        val volumeQuoute: kotlin.String? = null,
        /* Actual timestamp. */
        @Json(name = "timestamp")
        val timestamp: java.time.OffsetDateTime? = null
)

