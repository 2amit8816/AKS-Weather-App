package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.repository.LocalRepository
import javax.inject.Inject

class SaveWeatherLocationUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository
) : SaveWeatherLocationUseCase {
    override suspend fun invoke(location: LocationEntity) {
        localRepository.saveLocation(location)
    }
}