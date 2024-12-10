package com.example.aksweatherapp.di.source

import com.example.aksweatherapp.data.remote.RemoteDataSource
import com.example.aksweatherapp.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(
        dataSource: RemoteDataSourceImpl,
    ): RemoteDataSource
}