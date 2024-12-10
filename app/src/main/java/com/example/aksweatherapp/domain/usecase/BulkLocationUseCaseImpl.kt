package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BulkLocationUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    BulkLocationUseCase {
    override suspend fun invoke(bulkRequestBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>> {
        return repository.getBulkDataFromApi(bulkRequestBody)
    }
}