package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface AllSavedLocationsListUseCase {
    suspend operator fun invoke(): Flow<NetworkResponseState<List<LocationEntity>>>
}