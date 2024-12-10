package com.example.aksweatherapp.domain.usecase

import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import kotlinx.coroutines.flow.Flow

interface BulkLocationUseCase {
    suspend operator fun invoke(bulkRequestBody: BulkRequestBody): Flow<NetworkResponseState<BulkResponseBody>>
}