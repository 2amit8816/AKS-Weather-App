package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BulkLocationUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    BulkLocationUseCase {
    override suspend fun invoke(latList: String, lonList: String): Flow<NetworkResponseState<List<CurrentWeather>>> {
        return repository.getBulkDataFromApi(latList, lonList)
    }
}