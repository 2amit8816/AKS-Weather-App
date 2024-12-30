package com.example.aksweatherapp.data.remote

import com.example.aksweatherapp.common.GeoService
import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.common.WeatherService
import com.example.aksweatherapp.data.api.GeoApiService
import com.example.aksweatherapp.data.api.WeatherApiService
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.data.dto.LocationSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    @GeoService private val geoApiService: GeoApiService,
    @WeatherService private val weatherApiService: WeatherApiService
) : RemoteDataSource {
    override fun getLocationListFromApi(searchQuery: String): Flow<NetworkResponseState<LocationSearchResult>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = geoApiService.getLocationsListOnQueryFromApi(searchQuery)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getCurrentWeatherDataFromApi(
        lat: String,
        lon: String
    ): Flow<NetworkResponseState<CurrentWeather>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = weatherApiService.getCurrentWeatherDataFromApi(lat, lon)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getBulkDataFromApi(
        latList: String,
        lonList: String
    ): Flow<NetworkResponseState<List<CurrentWeather>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response =
                    weatherApiService.getBulkDataFromApi(latList, lonList)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    override fun getAstroDataFromApi(
        lat: String,
        lon: String
    ): Flow<NetworkResponseState<CurrentWeather>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response =
                    weatherApiService.getAstronomyDataFromApi(lat, lon)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

}