package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AstronomyBody(
    @Json(name = "location")
    val location: WeatherLocation,

    @Json(name = "astronomy")
    val astronomy: Astronomy,
)

@JsonClass(generateAdapter = true)
data class Astronomy(
    @Json(name = "astro")
    val astro: AstronomyData,
)

@JsonClass(generateAdapter = true)
data class AstronomyData(
    @Json(name = "sunrise")
    val sunrise: String,

    @Json(name = "sunset")
    val sunset: String,
)
