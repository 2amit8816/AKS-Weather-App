package com.example.aksweatherapp.data.api

import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.Weather
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("search.json")
    suspend fun getLocationsListOnQueryFromApi(@Query("q") query: String): List<Location>

    @GET("current.json")
    suspend fun getCurrentWeatherDataFromApi(@Query("q") query: String): Weather

    @POST("current.json")
    suspend fun getBulkDataFromApi(
        @Body body: BulkRequestBody,
        @Query("q") query: String
    ): BulkResponseBody

    @GET("astronomy.json")
    suspend fun getAstronomyDataFromApi(
        @Query("q") query: String,
        @Query("dt") date: String
    ): AstronomyBody
}