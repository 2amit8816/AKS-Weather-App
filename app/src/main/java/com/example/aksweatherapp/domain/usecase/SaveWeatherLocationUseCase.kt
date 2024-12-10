package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.domain.entity.LocationEntity

interface SaveWeatherLocationUseCase {
    suspend operator fun invoke(location: LocationEntity)
}