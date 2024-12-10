package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
    suspend operator fun invoke(latlon: String): Flow<NetworkResponseState<Weather>>
}