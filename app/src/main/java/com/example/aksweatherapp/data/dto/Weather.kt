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


@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "latitude")
    val latitude: Float?,

    @Json(name = "longitude")
    val longitude: Float?,

    @Json(name = "current")
    val current: Current?,

    @Json(name = "daily")
    val daily: Daily?,
)

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "time")
    val time: String?,

    @Json(name = "apparent_temperature")
    val apparentTemperature: Float?,

    @Json(name = "precipitation")
    val precipitation: Float?,

    @Json(name = "weather_code")
    val weatherCode: Int?,

    @Json(name = "wind_speed_10m")
    val windSpeed10m: Float?,
)

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "uv_index_max")
    val uvIndexMax: List<Float?>?,

    @Json(name = "sunrise")
    val sunrise: List<Long?>?,

    @Json(name = "sunset")
    val sunset: List<Long?>?
)