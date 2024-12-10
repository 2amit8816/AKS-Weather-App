package com.example.aksweatherapp.data.repository

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.local.LocalDataSource
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : LocalRepository {
    override suspend fun saveLocation(location: LocationEntity) {
        withContext(Dispatchers.IO) {
            localDataSource.saveLocation(location)
        }
    }

    override suspend fun getSavedLocations(): Flow<NetworkResponseState<List<LocationEntity>>> {
        return localDataSource.getSavedLocations().map {
            NetworkResponseState.Success(it)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getWeatherByLocationId(locationId: String): Flow<NetworkResponseState<LocationEntity>> {
        return localDataSource.getWeatherByLocationId(locationId).map {
            NetworkResponseState.Success(it)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteLocation(locationId: String) {
        withContext(Dispatchers.IO) {
            localDataSource.deleteLocation(locationId)
        }
    }
}