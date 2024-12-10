package com.example.aksweatherapp.domain.usecase

interface DeleteLocationFromDBUseCase {
    suspend operator fun invoke(locationId: String)
}