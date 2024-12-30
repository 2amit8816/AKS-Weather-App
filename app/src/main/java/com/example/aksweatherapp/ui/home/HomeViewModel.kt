package com.example.aksweatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksweatherapp.common.NetworkResponseState
import com.example.aksweatherapp.common.UiState
import com.example.aksweatherapp.data.dto.BulkWeatherData
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.data.dto.Location
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Home Screen
 * @property locationListUseCase UseCase for getting location list
 * @property saveWeatherLocationUseCase UseCase for saving location
 * @property weatherUseCaseImpl UseCase for getting weather data
 * @property allSavedLocationsListUseCase UseCase for getting all saved locations
 * @property bulkLocationUseCase UseCase for getting bulk weather data
 * @property deleteLocationFromDBUseCase UseCase for deleting location from DB
 * @property astroDataUseCase UseCase for getting astronomy data
 */
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

    private val _locationsList: MutableStateFlow<UiState<List<Location?>>> =
        MutableStateFlow(UiState.Loading)
    val locationsList: StateFlow<UiState<List<Location?>>> = _locationsList

    private val _currentWeather: MutableStateFlow<UiState<CurrentWeather>> =
        MutableStateFlow(UiState.Loading)
    val currentWeather: StateFlow<UiState<CurrentWeather>> = _currentWeather

    private val _savedLocationsList: MutableStateFlow<UiState<List<LocationEntity>>> =
        MutableStateFlow(UiState.Loading)
    val savedLocationsList: StateFlow<UiState<List<LocationEntity>>> = _savedLocationsList

    private val _bulkWeatherList: MutableStateFlow<UiState<List<BulkWeatherData>>> =
        MutableStateFlow(UiState.Loading)
    val bulkWeatherList: StateFlow<UiState<List<BulkWeatherData>>> = _bulkWeatherList

    private val _weatherDetailsList = MutableStateFlow(emptyArray<WeatherDetails>())
    val weatherDetailsList: StateFlow<Array<WeatherDetails>> = _weatherDetailsList.asStateFlow()

    private val _astroDataState = MutableStateFlow<UiState<CurrentWeather>>(UiState.Loading)
    val astroDataState: StateFlow<UiState<CurrentWeather>> = _astroDataState.asStateFlow()

    /**
     * Update input text
     * @param inputText input text
     */
    fun updateInput(inputText: String) {
        if (inputText.isEmpty()) {
            clearSearch()
        } else {
            _inputText.value = inputText
        }
    }

    /**
     * Clear search
     */
    fun clearSearch() {
        _inputText.value = ""
        _locationsList.value = UiState.Success(emptyList())
    }

    fun getLocationSuggetionList() {
        viewModelScope.launch {
            inputText.debounce(timeoutMillis = 500).distinctUntilChanged().collectLatest { input ->
                if (input.isNotEmpty()) {
                    locationListUseCase.invoke(inputText.value).collect {
                        when (it) {
                            is NetworkResponseState.Loading -> {
                                _locationsList.value = UiState.Loading
                            }

                            is NetworkResponseState.Success -> {
                                it.result.results?.let {
                                    _locationsList.value = UiState.Success(it)
                                }
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

    fun getWeatherByLatLonFrom(location: Location) {
        viewModelScope.launch {
            weatherUseCaseImpl.invoke(location.lat.toString(), location.lon.toString()).collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        _currentWeather.value = UiState.Loading
                    }

                    is NetworkResponseState.Success -> {
                        clearSearch()
                        saveLocation(location)
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
        val latList =
            locationList.map { it.lat }.toString().removeSurrounding("[", "]").replace(" ", "")
        val lonList =
            locationList.map { it.lon }.toString().removeSurrounding("[", "]").replace(" ", "")

        viewModelScope.launch {
            if (locationList.size == 1) {
                weatherUseCaseImpl.invoke(locationList[0].lat.toString(), locationList[0].lon.toString()).collect {
                    when (it) {
                        is NetworkResponseState.Loading -> {
                            _bulkWeatherList.value = UiState.Loading
                        }

                        is NetworkResponseState.Success -> {
                            val data = it.result
                            val list: ArrayList<BulkWeatherData> = ArrayList()
                            list.add(
                                BulkWeatherData(
                                    id = locationList[0].id,
                                    location = locationList[0],
                                    weather = data
                                )
                            )
                            _bulkWeatherList.value = UiState.Success(list)
                        }

                        is NetworkResponseState.Error -> {
                            _bulkWeatherList.value =
                                UiState.Error(it.exception.message ?: "Error")
                        }
                    }
                }
            } else {
                bulkLocationUseCase.invoke(latList, lonList).collect {
                    when (it) {
                        is NetworkResponseState.Loading -> {
                            _bulkWeatherList.value = UiState.Loading
                        }

                        is NetworkResponseState.Success -> {
                            val list: ArrayList<BulkWeatherData> = ArrayList()
                            for (i in locationList.indices) {
                                list.add(
                                    BulkWeatherData(
                                        id = locationList[i].id,
                                        location = locationList[i],
                                        weather = it.result[i]
                                    )
                                )
                            }
                            _bulkWeatherList.value = UiState.Success(list)
                        }

                        is NetworkResponseState.Error -> {
                            _bulkWeatherList.value =
                                UiState.Error(it.exception.message ?: "Error")
                        }
                    }
                }
            }
        }
    }

    private fun saveLocation(location: Location?) {
        location?.let {
            viewModelScope.launch {
                saveWeatherLocationUseCase.invoke(LocationEntityMapperImpl().map(location))
            }
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

    suspend fun getAstronomyData(
        lat: String,
        lon: String
    ): Flow<UiState<CurrentWeather>> {
        return flow {
            astroDataUseCase.invoke(lat, lon).collect {
                when (it) {
                    is NetworkResponseState.Loading -> {
                        emit(UiState.Loading)
                    }

                    is NetworkResponseState.Success -> {
                        emit(UiState.Success(it.result))
                    }

                    is NetworkResponseState.Error -> {
                        emit(UiState.Error(it.exception.message ?: "Error"))
                    }
                }
            }
        }
    }
}