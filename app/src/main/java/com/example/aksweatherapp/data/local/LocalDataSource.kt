package com.example.aksweatherapp.data.local

import com.example.aksweatherapp.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveLocation(location: LocationEntity)
    suspend fun getSavedLocations(): Flow<List<LocationEntity>>
    suspend fun getWeatherByLocationId(locationId: String): Flow<LocationEntity>
    suspend fun deleteLocation(locationId: String)
}