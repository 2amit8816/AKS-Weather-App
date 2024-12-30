package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "id")
    val id: Int?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "admin1")
    val region: String?,

    @Json(name = "country")
    val country: String?,

    @Json(name = "latitude")
    val lat: Float?,

    @Json(name = "longitude")
    val lon: Float?,

    @Json(name = "admin3")
    val url: String?,

    @Json(name = "timezone")
    val timezone: String?,
)
