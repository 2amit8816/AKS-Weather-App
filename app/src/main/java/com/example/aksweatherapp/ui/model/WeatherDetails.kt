package com.example.aksweatherapp.ui.model

data class WeatherDetails(
    val locationId: String,
    val precipitation: String,
    val wind: String,
    val uvIndex: Float,
    val sunRise: String,
    val sunSet: String
)
