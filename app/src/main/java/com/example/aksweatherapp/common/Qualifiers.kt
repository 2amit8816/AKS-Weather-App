package com.example.aksweatherapp.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeoService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherService