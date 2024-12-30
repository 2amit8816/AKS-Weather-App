package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.LocationSearchResult
import kotlinx.coroutines.flow.Flow

interface LocationListUseCase {
    suspend operator fun invoke(searchQuery: String): Flow<NetworkResponseState<LocationSearchResult>>
}