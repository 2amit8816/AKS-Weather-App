package com.example.aksweatherapp.di.usecase

import com.example.aksweatherapp.domain.usecase.AllSavedLocationsListUseCase
import com.example.aksweatherapp.domain.usecase.AllSavedLocationsListUseCaseImpl
import com.example.aksweatherapp.domain.usecase.AstroDataUseCase
import com.example.aksweatherapp.domain.usecase.AstroDataUseCaseImpl
import com.example.aksweatherapp.domain.usecase.BulkLocationUseCase
import com.example.aksweatherapp.domain.usecase.BulkLocationUseCaseImpl
import com.example.aksweatherapp.domain.usecase.DeleteLocationFromDBUseCase
import com.example.aksweatherapp.domain.usecase.DeleteLocationFromDBUseCaseImpl
import com.example.aksweatherapp.domain.usecase.GetWeatherUseCase
import com.example.aksweatherapp.domain.usecase.GetWeatherUseCaseImpl
import com.example.aksweatherapp.domain.usecase.LocationListUseCase
import com.example.aksweatherapp.domain.usecase.LocationListUseCaseImpl
import com.example.aksweatherapp.domain.usecase.SaveWeatherLocationUseCase
import com.example.aksweatherapp.domain.usecase.SaveWeatherLocationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLocationListUseCase(
        locationListUseCaseImpl: LocationListUseCaseImpl,
    ): LocationListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetWeatherUseCase(
        getWeatherUseCaseImpl: GetWeatherUseCaseImpl,
    ): GetWeatherUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSaveWeatherUseCase(
        saveWeatherLocationUseCaseImpl: SaveWeatherLocationUseCaseImpl,
    ): SaveWeatherLocationUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAllSavedLocationsListUseCase(
        allSavedLocationsListUseCaseImpl: AllSavedLocationsListUseCaseImpl,
    ): AllSavedLocationsListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindBulkUseCase(
        bulkLocationUseCaseImpl: BulkLocationUseCaseImpl,
    ): BulkLocationUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteLocationFromDBUseCase(
        deleteLocationFromDBUseCaseImpl: DeleteLocationFromDBUseCaseImpl,
    ): DeleteLocationFromDBUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAstroDataUseCase(
        astroDataUseCaseImpl: AstroDataUseCaseImpl,
    ): AstroDataUseCase
}
