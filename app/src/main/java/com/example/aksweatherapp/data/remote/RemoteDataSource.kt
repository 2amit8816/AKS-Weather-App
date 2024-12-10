package com.example.aksweatherapp.data.remote

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getLocationListFromApi(searchQuery: String): Flow<NetworkResponseState<List<Location>>>

    fun getCurrentWeatherDataFromApi(latlon: String): Flow<NetworkResponseState<Weather>>

    fun getBulkDataFromApi(locationBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>>

    fun getAstroDataFromApi(latlon: String, date: String): Flow<NetworkResponseState<AstronomyBody>>
}