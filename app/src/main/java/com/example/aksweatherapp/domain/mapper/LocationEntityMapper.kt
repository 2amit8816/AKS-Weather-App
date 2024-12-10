package com.example.aksweatherapp.domain.mapper

interface LocationEntityMapper<I, O> {
    fun map(input: I): O
}