package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AstroDataUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    AstroDataUseCase {
    override suspend fun invoke(
        lat: String,
        lon: String
    ): Flow<NetworkResponseState<CurrentWeather>> {
        return repository.getAstroDataFromApi(lat, lon)
    }
}