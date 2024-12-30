package com.example.aksweatherapp.data.dto

import com.example.aksweatherapp.domain.entity.LocationEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BulkResponseBody(
    @Json(name = "bulk")
    val list: List<BulkBody>,
)

@JsonClass(generateAdapter = true)
data class BulkBody(
    @Json(name = "query")
    val query: WeatherBody,
)


@JsonClass(generateAdapter = true)
data class WeatherBody(
    @Json(name = "custom_id")
    val id: String,

    @Json(name = "location")
    val location: WeatherLocation,

    @Json(name = "current")
    val current: WeatherData,
)

@JsonClass(generateAdapter = true)
data class BulkWeatherData(
    val id: String?,
    val location: LocationEntity,
    val weather: CurrentWeather
)
