package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BulkRequestBody(
    @Json(name = "locations")
    val locations: List<LocationBody>
)

@JsonClass(generateAdapter = true)
data class LocationBody(
    @Json(name = "q")
    val query: String? = null,

    @Json(name = "custom_id")
    val customId: String? = null
)
