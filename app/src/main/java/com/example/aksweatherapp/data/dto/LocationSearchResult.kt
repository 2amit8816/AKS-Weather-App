package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationSearchResult(
    @Json(name = "results")
    val results: List<Location?>?
)
