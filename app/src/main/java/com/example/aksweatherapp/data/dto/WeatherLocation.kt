package com.example.aksweatherapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherLocation(
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

    @Json(name = "tz_id")
    val tzId: String,

    @Json(name = "localtime_epoch")
    val localtimeEpoch: Long,

    @Json(name = "localtime")
    val localtime: String,
) {
    val regionName: String
        get() = if (region.isNotEmpty()) {
            "$name, $region"
        } else {
            name
        }
}
