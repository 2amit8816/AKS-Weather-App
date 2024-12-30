package com.example.aksweatherapp.common

object WeatherIconUtility {
    private fun getWeatherIconCode(weatherCode: Int?): String {
        return when (weatherCode) {
            0, 1 -> "01d"
            2 -> "03d"
            3 -> "04d"
            45, 48 -> "50d"
            51, 53, 55, 61, 63, 65, 66, 67, 80, 81, 82 -> "09d"
            56, 57, 71, 73, 75, 77, 85, 86 -> "13d"
            95, 96, 99 -> "11d"
            else -> "01d"
        }
    }

    fun getWeatherIconUrl(weatherCode: Int?): String {
        return "https://openweathermap.org/img/w/${getWeatherIconCode(weatherCode)}.png"
    }
}