package com.example.aksweatherapp.common

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
    data class Success<out T : Any>(val uiData: T) : UiState<T>()
}