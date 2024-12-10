package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "region")
    val region: String,

    @Json(name = "country")
    val country: String,

    @Json(name = "lat")
    val lat: Float,

    @Json(name = "lon")
    val lon: Float,

    @Json(name = "url")
    val url: String,
)
