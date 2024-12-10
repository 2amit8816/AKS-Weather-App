package com.example.aksweatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.common.UiState
import com.example.aksweatherapp.data.dto.AstronomyBody
import com.example.aksweatherapp.data.dto.BulkRequestBody
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.data.dto.LocationBody
import com.example.aksweatherapp.data.dto.Weather
import com.example.aksweatherapp.data.dto.WeatherData
import com.example.aksweatherapp.data.mapper.LocationEntityMapperImpl
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.domain.usecase.AllSavedLocationsListUseCase
import com.example.aksweatherapp.domain.usecase.AstroDataUseCase
import com.example.aksweatherapp.domain.usecase.BulkLocationUseCase
import com.example.aksweatherapp.domain.usecase.DeleteLocationFromDBUseCase
import com.example.aksweatherapp.domain.usecase.GetWeatherUseCaseImpl
import com.example.aksweatherapp.domain.usecase.LocationListUseCase
import com.example.aksweatherapp.domain.usecase.SaveWeatherLocationUseCase
import com.example.aksweatherapp.ui.model.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationListUseCase: LocationListUseCase,
    private val saveWeatherLocationUseCase: SaveWeatherLocationUseCase,
    private val weatherUseCaseImpl: GetWeatherUseCaseImpl,
    private val allSavedLocationsListUseCase: AllSavedLocationsListUseCase,
    private val bulkLocationUseCase: BulkLocationUseCase,
    private val deleteLocationFromDBUseCase: DeleteLocationFromDBUseCase,
    private val astroDataUseCase: AstroDataUseCase
) : ViewModel() {
    private val _inputText: MutableStateFlow<String> = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _locationsList: MutableStateFlow<UiState<List<Location>>> =
        MutableStateFlow(UiState.Loading)
    val locationsList: StateFlow<UiState<List<Location>>> = _locationsList

    private val _currentWeather: MutableStateFlow<UiState<Weather>> =
        MutableStateFlow(UiState.Loading)
    val currentWeather: StateFlow<UiState<Weather>> = _currentWeather

    private val _savedLocationsList: MutableStateFlow<UiState<List<LocationEntity>>> =
        MutableStateFlow(UiState.Loading)
    val savedLocationsList: StateFlow<UiState<List<LocationEntity>>> = _savedLocationsList

    private val _bulkWeatherList: MutableStateFlow<UiState<BulkResponseBody>> =
        MutableStateFlow(UiState.Loading)
    val bulkWeatherList: StateFlow<UiState<BulkResponseBody>> = _bulkWeatherList

    private val _weatherDetailsList = MutableStateFlow(emptyArray<WeatherDetails>())
    val weatherDetailsList: StateFlow<Array<WeatherDetails>> = _weatherDetailsList.asStateFlow()

    private val _astroDataState = MutableStateFlow<UiState<AstronomyBody>>(UiState.Loading)
    val astroDataState: StateFlow<UiState<AstronomyBody>> = _astroDataState.asStateFlow()

    fun updateInput(inputText: String) {
        if (inputText.isEmpty()) {
            clearSearch()
        } else {
            _inputText.value = inputText
        }
    }

    fun clearSearch() {
        _inputText.value = ""
        _locationsList.value = UiState.Success(emptyList())
    }

    fun getLocationSuggetionList(searchQuery: String) {
        viewModelScope.launch {
            inputText.debounce(timeoutMillis = 500).distinctUntilChanged().collectLatest { input ->
                if (input.isNotEmpty()) {
                    locationListUseCase.invoke(searchQuery).collect {
                        when (it) {
                            is NetworkResponseState.Loading -> {
                                _locationsList.value = UiState.Loading
                            }

                            is NetworkResponseState.Success -> {
                                _locationsList.value = UiState.Success(it.result)
                            }

                            is NetworkResponseState.Error -> {
                                _locationsList.value =
                                    UiState.Error(it.exception.message ?: "Error")
                            }
                        }
                    }
                }
            }
        }
    }

    fun getLocationByLatLonFrom(latlon: String) {
        viewModelScope.launch {
            weatherUseCaseImpl.invoke(latlon).collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        _currentWeather.value = UiState.Loading
                    }

                    is NetworkResponseState.Success -> {
                        _currentWeather.value = UiState.Success(it.result)
                    }

                    is NetworkResponseState.Error -> {
                        _currentWeather.value =
                            UiState.Error(it.exception.message ?: "Error")
                    }
                }
            }
        }
    }

    fun getBulkWeatherData(locationList: List<LocationEntity>) {
        val locationBodyList = mutableListOf<LocationBody>()
        locationList.map {
            LocationBody(
                query = "${it.lat},${it.lon}",
                customId = it.id.toString()
            )
        }.forEach { locationBodyList.add(it) }
        viewModelScope.launch {
            bulkLocationUseCase.invoke(BulkRequestBody(locationBodyList)).collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        _bulkWeatherList.value = UiState.Loading
                    }

                    is NetworkResponseState.Success -> {
                        _bulkWeatherList.value = UiState.Success(it.result)
                    }

                    is NetworkResponseState.Error -> {
                        _bulkWeatherList.value =
                            UiState.Error(it.exception.message ?: "Error")
                    }
                }
            }
        }
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            saveWeatherLocationUseCase.invoke(LocationEntityMapperImpl().map(location))
        }
    }

    fun getAllSavedLocations() {
        viewModelScope.launch {
            allSavedLocationsListUseCase.invoke().collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        _savedLocationsList.value = UiState.Loading
                    }

                    is NetworkResponseState.Success -> {
                        _savedLocationsList.value = UiState.Success(it.result)
                    }

                    is NetworkResponseState.Error -> {
                        _savedLocationsList.value =
                            UiState.Error(it.exception.message ?: "Error")
                    }
                }
            }
        }
    }

    fun deleteSavedLocation(locationId: String) {
        viewModelScope.launch {
            deleteLocationFromDBUseCase.invoke(locationId)
        }
    }

    fun getLoaddedAstronomyData(locationId: String): WeatherDetails? {
        val astroData = (_weatherDetailsList.value).firstOrNull {
            it.locationId == locationId
        }
        return astroData
    }

    fun getAstronomyData(
        weatherData: WeatherData,
        locationId: String,
        latlon: String,
        date: String
    ) {
        viewModelScope.launch {
            astroDataUseCase.invoke(latlon, date).collect {
                when (it) {

                    is NetworkResponseState.Loading -> {
                        _astroDataState.value = UiState.Loading
                    }

                    is NetworkResponseState.Success -> {
                        val data = it.result
                        _astroDataState.value = UiState.Success(data)
                        _weatherDetailsList.update { list ->
                            list.plus(
                                WeatherDetails(
                                    locationId = locationId,
                                    sunRise = data.astronomy.astro.sunrise,
                                    sunSet = data.astronomy.astro.sunset,
                                    precipitation = weatherData.precipitation.toString(),
                                    uvIndex = weatherData.uv,
                                    wind = weatherData.windKph.toString(),
                                )
                            )
                        }
                    }

                    is NetworkResponseState.Error -> {
                        _astroDataState.value = UiState.Error(it.exception.message ?: "Error")
                    }
                }
            }
        }
    }
}