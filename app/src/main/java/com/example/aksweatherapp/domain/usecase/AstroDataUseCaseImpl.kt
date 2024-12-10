package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AstroDataUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    AstroDataUseCase {
    override suspend fun invoke(
        latlon: String,
        date: String
    ): Flow<NetworkResponseState<AstronomyBody>> {
        return repository.getAstroDataFromApi(latlon, date)
    }
}