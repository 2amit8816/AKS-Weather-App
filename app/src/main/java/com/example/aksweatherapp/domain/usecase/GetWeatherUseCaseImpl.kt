package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.Weather
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCaseImpl @Inject constructor(private val repository: RemoteRepository): GetWeatherUseCase {
    override suspend fun invoke(latlon: String): Flow<NetworkResponseState<Weather>> {
        return repository.getCurrentWeather(latlon)
    }
}