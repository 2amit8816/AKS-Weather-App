package com.example.aksweatherapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_locations")
data class LocationEntity(
    @PrimaryKey
    @ColumnInfo(name = "location_id")
    var id: String,

    @ColumnInfo(name = "location_name")
    val name: String?,

    @ColumnInfo(name = "location_region")
    val region: String?,

    @ColumnInfo(name = "location_country")
    val country: String?,

    @ColumnInfo(name = "location_lat")
    val lat: Float?,

    @ColumnInfo(name = "location_lon")
    val lon: Float?,

    @ColumnInfo(name = "location_url")
    val url: String?,

    @ColumnInfo(name = "location_timezone")
    val timezone: String?,
)
