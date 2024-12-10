package com.example.aksweatherapp.di.common

import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.aksweatherapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    fun provideFont(): GoogleFont.Provider {
        return GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )
    }
}