package com.example.aksweatherapp.data.repository

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.Weather
import com.example.aksweatherapp.data.remote.RemoteDataSource
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): RemoteRepository {
    override suspend fun getLocationsList(searchQuery: String): Flow<NetworkResponseState<List<Location>>> {
        return remoteDataSource.getLocationListFromApi(searchQuery).map {
            when (it) {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCurrentWeather(latlon: String): Flow<NetworkResponseState<Weather>> {
        return remoteDataSource.getCurrentWeatherDataFromApi(latlon).map {
            when (it) {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getBulkDataFromApi(locationBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>> {
        return remoteDataSource.getBulkDataFromApi(locationBody).map {
            when (it) {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAstroDataFromApi(
        latlon: String,
        date: String
    ): Flow<NetworkResponseState<AstronomyBody>> {
        return remoteDataSource.getAstroDataFromApi(latlon, date).map {
            when (it) {
                is NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)
                is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
            }
        }.flowOn(Dispatchers.IO)
    }
}