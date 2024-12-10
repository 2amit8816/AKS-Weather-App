package com.example.aksweatherapp.di.mappers

import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.mapper.LocationEntityMapper
import com.example.aksweatherapp.domain.mapper.LocationListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindLocationEntityMapper(locationEntityMapper: LocationEntityMapper<Location, LocationEntity>): LocationEntityMapper<Location, LocationEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindLocationListMapper(locationListMapper: LocationListMapper<Location, LocationEntity>): LocationListMapper<Location, LocationEntity>

}