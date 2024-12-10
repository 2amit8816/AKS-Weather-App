package com.example.aksweatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aksweatherapp.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherLocationInfo(locationEntity: LocationEntity)

    @Query("SELECT * FROM saved_locations WHERE location_id = :locationId")
    fun getLocationById(locationId: String): Flow<LocationEntity>

    @Query("SELECT * FROM saved_locations")
    fun getAllSavedLocations(): Flow<List<LocationEntity>>

    @Query("DELETE FROM saved_locations WHERE location_id = :locationId")
    suspend fun deleteLocation(locationId: String)
}