package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.BulkWeatherData
import com.example.aksweatherapp.data.dto.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface BulkLocationUseCase {
    suspend operator fun invoke(latList: String, lonList: String): Flow<NetworkResponseState<List<CurrentWeather>>>
}