package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllSavedLocationsListUseCaseImpl @Inject constructor(private val repository: LocalRepository) :
    AllSavedLocationsListUseCase {
    override suspend fun invoke(): Flow<NetworkResponseState<List<LocationEntity>>> {
        return repository.getSavedLocations()
    }
}