package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteLocationFromDBUseCaseImpl @Inject constructor(private val repository: LocalRepository): DeleteLocationFromDBUseCase {
    override suspend fun invoke(locationId: String) {
        return repository.deleteLocation(locationId)
    }
}