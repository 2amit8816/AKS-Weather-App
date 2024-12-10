package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "location")
    val location: WeatherLocation,

    @Json(name = "current")
    val current: WeatherData,
)

@JsonClass(generateAdapter = true)
data class WeatherData(
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Long,

    @Json(name = "last_updated")
    val lastUpdated: String,

    @Json(name = "temp_c")
    val tempC: String,

    @Json(name = "condition")
    val condition: Condition,

    @Json(name = "precip_mm")
    val precipitation: Float,

    @Json(name = "wind_kph")
    val windKph: Float,

    @Json(name = "uv")
    val uv: Float,
)

@JsonClass(generateAdapter = true)
data class Condition(
    @Json(name = "text")
    val text: String,

    @Json(name = "icon")
    val icon: String,
)
