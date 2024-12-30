package com.example.aksweatherapp.common

object Constants {
    //API Constants
    private const val WEATHER_API_VER = "v1"

    const val GEO_BASE_URL = "https://geocoding-api.open-meteo.com/${WEATHER_API_VER}/"
    const val WEATHER_BASE_URL = "https://api.open-meteo.com/${WEATHER_API_VER}//"

    const val TEMP_PARAM = "apparent_temperature"
    const val PRECIPITATION_PARAM = "precipitation"
    const val WEATHER_CODE_PARAM = "weather_code"
    const val WIND_SPEED_PARAM = "wind_speed_10m"
    const val UV_INDEX_PARAM = "uv_index_max"
    const val TIME_FORMAT_PARAM = "unixtime"
    const val FORECAST_DAYS_PARAM = 1
    const val SUNRISE_PARAM = "sunrise"
    const val SUNSET_PARAM = "sunset"

    //Preference Constants
    const val PLACES_PREFERENCE = "placePreference"
}