package com.example.aksweatherapp.data.remote

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.LocationSearchResult
import com.example.aksweatherapp.data.dto.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getLocationListFromApi(searchQuery: String): Flow<NetworkResponseState<LocationSearchResult>>

    fun getCurrentWeatherDataFromApi(lat: String, lon: String): Flow<NetworkResponseState<CurrentWeather>>

    fun getBulkDataFromApi(latList: String, lonList: String): Flow<NetworkResponseState<List<CurrentWeather>>>

    fun getAstroDataFromApi(latlon: String, date: String): Flow<NetworkResponseState<CurrentWeather>>
}