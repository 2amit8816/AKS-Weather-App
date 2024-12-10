package com.example.aksweatherapp.domain.repository

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getLocationsList(searchQuery: String): Flow<NetworkResponseState<List<Location>>>

    suspend fun getCurrentWeather(latlon: String): Flow<NetworkResponseState<Weather>>

    suspend fun getBulkDataFromApi(locationBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>>

    suspend fun getAstroDataFromApi(latlon: String, date: String): Flow<NetworkResponseState<AstronomyBody>>
}