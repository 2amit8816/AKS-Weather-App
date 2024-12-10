package com.example.aksweatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aksweatherapp.domain.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}