package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationListUseCaseImpl @Inject constructor(private val repository: RemoteRepository): LocationListUseCase {
    override suspend fun invoke(searchQuery: String): Flow<NetworkResponseState<List<Location>>> {
        return repository.getLocationsList(searchQuery)
    }
}