package com.example.aksweatherapp.data.mapper

import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.mapper.LocationListMapper

class LocationListMapperImpl : LocationListMapper<Location, LocationEntity> {
    override fun map(input: List<Location>): List<LocationEntity> {
        return input.map { location ->
            LocationEntity(
                id = location.id.toString(),
                name = location.name,
                region = location.region,
                country = location.country,
                lat = location.lat,
                lon = location.lon,
                url = location.url
            )
        }
    }
}
