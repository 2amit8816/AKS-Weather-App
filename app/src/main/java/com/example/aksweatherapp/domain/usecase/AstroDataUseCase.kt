package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface AstroDataUseCase {
    suspend operator fun invoke(latlon: String, date: String): Flow<NetworkResponseState<CurrentWeather>>
}