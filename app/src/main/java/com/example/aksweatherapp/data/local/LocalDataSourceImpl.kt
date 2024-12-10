package com.example.aksweatherapp.data.local

import com.example.aksweatherapp.data.database.AppDao
import com.example.aksweatherapp.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDao: AppDao) : LocalDataSource {
    override suspend fun saveLocation(location: LocationEntity) {
        return appDao.insertWeatherLocationInfo(location)
    }

    override suspend fun getSavedLocations(): Flow<List<LocationEntity>> {
        return appDao.getAllSavedLocations()
    }

    override suspend fun getWeatherByLocationId(locationId: String): Flow<LocationEntity> {
        return appDao.getLocationById(locationId)
    }

    override suspend fun deleteLocation(locationId: String) {
        return appDao.deleteLocation(locationId)
    }
}