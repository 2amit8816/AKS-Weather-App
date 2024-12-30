package com.example.aksweatherapp.data.api

import com.example.aksweatherapp.common.Constants.FORECAST_DAYS_PARAM
import com.example.aksweatherapp.common.Constants.PRECIPITATION_PARAM
import com.example.aksweatherapp.common.Constants.SUNRISE_PARAM
import com.example.aksweatherapp.common.Constants.SUNSET_PARAM
import com.example.aksweatherapp.common.Constants.TEMP_PARAM
import com.example.aksweatherapp.common.Constants.TIME_FORMAT_PARAM
import com.example.aksweatherapp.common.Constants.UV_INDEX_PARAM
import com.example.aksweatherapp.common.Constants.WEATHER_CODE_PARAM
import com.example.aksweatherapp.common.Constants.WIND_SPEED_PARAM
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.data.dto.LocationSearchResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GeoApiService {
    @GET("search")
    suspend fun getLocationsListOnQueryFromApi(@Query("name") query: String): LocationSearchResult
}

interface WeatherApiService {
    @GET("forecast")
    suspend fun getCurrentWeatherDataFromApi(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("current") current: String = "${TEMP_PARAM},${PRECIPITATION_PARAM},${WEATHER_CODE_PARAM},${WIND_SPEED_PARAM}",
        @Query("daily") daily: String = UV_INDEX_PARAM,
        @Query("forecast_days") forecastDays: Int = FORECAST_DAYS_PARAM,
        @Query("timeformat") astro: String = TIME_FORMAT_PARAM,
    ): CurrentWeather

    @GET("forecast")
    suspend fun getBulkDataFromApi(
        @Query("latitude") latitudeList: String,
        @Query("longitude") longitudeList: String,
        @Query("current") current: String = "${TEMP_PARAM},${PRECIPITATION_PARAM},${WEATHER_CODE_PARAM},${WIND_SPEED_PARAM}",
        @Query("daily") daily: String = UV_INDEX_PARAM,
        @Query("forecast_days") forecastDays: Int = FORECAST_DAYS_PARAM,
        @Query("timeformat") astro: String = TIME_FORMAT_PARAM,
    ): List<CurrentWeather>

    @GET("forecast")
    suspend fun getAstronomyDataFromApi(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("current") current: String = "${TEMP_PARAM},${PRECIPITATION_PARAM},${WEATHER_CODE_PARAM},${WIND_SPEED_PARAM}",
        @Query("daily") daily: String = "${UV_INDEX_PARAM},${SUNRISE_PARAM},${SUNSET_PARAM}",
        @Query("forecast_days") forecastDays: Int = FORECAST_DAYS_PARAM,
        @Query("timeformat") astro: String = TIME_FORMAT_PARAM,
    ): CurrentWeather
}