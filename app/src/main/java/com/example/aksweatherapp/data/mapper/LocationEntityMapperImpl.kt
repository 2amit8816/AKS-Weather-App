package com.example.aksweatherapp.data.mapper

import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.mapper.LocationEntityMapper

class LocationEntityMapperImpl : LocationEntityMapper<Location, LocationEntity>{
    override fun map(input: Location): LocationEntity {
        return LocationEntity(
            id = input.id.toString(),
            name = input.name,
            region = input.region,
            country = input.country,
            lat = input.lat,
            lon = input.lon,
            url = input.url,
            timezone = input.timezone
        )
    }
}