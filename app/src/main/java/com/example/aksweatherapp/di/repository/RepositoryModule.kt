package com.example.aksweatherapp.di.repository

import com.example.aksweatherapp.data.repository.LocalRepositoryImpl
import com.example.aksweatherapp.data.repository.RemoteRepositoryImpl
import com.example.aksweatherapp.domain.repository.LocalRepository
import com.example.aksweatherapp.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteRepository(
        repository: RemoteRepositoryImpl,
    ): RemoteRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLocalRepository(
        repository: LocalRepositoryImpl,
    ): LocalRepository
}