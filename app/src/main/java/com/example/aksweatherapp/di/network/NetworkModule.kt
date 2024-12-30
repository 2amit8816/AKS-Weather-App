package com.example.aksweatherapp.di.network

import com.example.aksweatherapp.common.Constants.GEO_BASE_URL
import com.example.aksweatherapp.common.Constants.WEATHER_BASE_URL
import com.example.aksweatherapp.common.GeoService
import com.example.aksweatherapp.common.TokenManager
import com.example.aksweatherapp.common.WeatherService
import com.example.aksweatherapp.data.api.AuthInterceptor
import com.example.aksweatherapp.data.api.GeoApiService
import com.example.aksweatherapp.data.api.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideTokenManager(): TokenManager {
        return TokenManager()
    }

    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }

    @GeoService
    @Provides
    @Singleton
    fun provideGeoService(moshi: Moshi, okHttpClient: OkHttpClient): GeoApiService {
        return Retrofit.Builder()
            .baseUrl(GEO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GeoApiService::class.java)
    }

    @WeatherService
    @Provides
    @Singleton
    fun provideWeatherService(moshi: Moshi, okHttpClient: OkHttpClient): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherApiService::class.java)
    }
}