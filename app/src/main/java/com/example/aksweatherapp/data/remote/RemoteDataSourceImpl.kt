package com.example.aksweatherapp.data.remote

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.api.ApiService
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.LocationBody
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteDataSource{
    override fun getLocationListFromApi(searchQuery: String): Flow<NetworkResponseState<List<Location>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getLocationsListOnQueryFromApi(searchQuery)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getCurrentWeatherDataFromApi(latlon: String): Flow<NetworkResponseState<Weather>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getCurrentWeatherDataFromApi(latlon)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getBulkDataFromApi(locationBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getBulkDataFromApi(query = "bulk", body = locationBody)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getAstroDataFromApi(latlon: String, date: String): Flow<NetworkResponseState<AstronomyBody>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getAstronomyDataFromApi(query = latlon, date = date)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

}