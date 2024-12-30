package com.example.aksweatherapp.domain.repository

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.LocationSearchResult
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getLocationsList(searchQuery: String): Flow<NetworkResponseState<LocationSearchResult>>

    suspend fun getCurrentWeather(lat: String, lon: String): Flow<NetworkResponseState<CurrentWeather>>

    suspend fun getBulkDataFromApi(latList: String, lonList: String): Flow<NetworkResponseState<List<CurrentWeather>>>

    suspend fun getAstroDataFromApi(latlon: String, date: String): Flow<NetworkResponseState<CurrentWeather>>
}