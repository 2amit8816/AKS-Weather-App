package com.example.aksweatherapp.domain.repository

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveLocation(location: LocationEntity)
    suspend fun getSavedLocations(): Flow<NetworkResponseState<List<LocationEntity>>>
    suspend fun getWeatherByLocationId(locationId: String): Flow<NetworkResponseState<LocationEntity>>
    suspend fun deleteLocation(locationId: String)
}