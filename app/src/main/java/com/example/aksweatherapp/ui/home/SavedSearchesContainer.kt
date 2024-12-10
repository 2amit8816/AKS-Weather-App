package com.example.aksweatherapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.aksweatherapp.R
import com.example.aksweatherapp.common.UiState
import com.example.aksweatherapp.data.dto.BulkResponseBody
import com.example.aksweatherapp.data.dto.WeatherBody
import com.example.aksweatherapp.domain.entity.LocationEntity
import com.example.aksweatherapp.ui.core.CustomFontSize
import com.example.aksweatherapp.ui.core.CustomSize
import com.example.aksweatherapp.ui.model.WeatherDetails
import com.example.aksweatherapp.ui.theme.fontFamily

@Composable
fun SavedSearchesContainer(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val savedLocationsList by viewModel.savedLocationsList.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllSavedLocations()
    }

    Box(
        modifier = modifier
            .background(Color.White)
    ) {
        when (savedLocationsList) {
            is UiState.Loading -> {
                Text("Loading")
            }

            is UiState.Success -> {
                val locations = (savedLocationsList as UiState.Success<List<LocationEntity>>).uiData

                if (locations.isEmpty()) {
                    EmptyWeatherState()
                } else {
                    viewModel.getBulkWeatherData(locations)
                    WeatherListView()
                }
            }

            is UiState.Error -> {
                Text("Error")
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun WeatherListView() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val savedWeatherList by viewModel.bulkWeatherList.collectAsState()

    when (savedWeatherList) {
        is UiState.Loading -> {
            Text("Loading")
        }

        is UiState.Success -> {
            val data = (savedWeatherList as UiState.Success<BulkResponseBody>).uiData
            LazyColumn {
                itemsIndexed(data.list) { _, weather ->
                    DismissableRow(weather.query) {
                        viewModel.deleteSavedLocation(it.id)
                    }
                }
            }
        }

        is UiState.Error -> {
            Text("Error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalUnitApi
@Composable
fun DismissableRow(weather: WeatherBody, onDelete: (WeatherBody) -> Unit) {
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        // Remove item from the list
        onDelete.invoke(weather)
    }
    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier
            .padding(vertical = Dp(1f)),
        directions = setOf(
            DismissDirection.EndToStart
        ),
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.White
                    else -> Color.Red
                }, label = ""
            )

            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            AnimatedVisibility(dismissState.targetValue != DismissValue.Default) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(horizontal = Dp(20f)),
                    contentAlignment = alignment
                ) {
                    Icon(
                        icon,
                        contentDescription = "Delete Icon",
                        tint = colorResource(R.color.white)
                    )
                }
            }
        },
        dismissContent = {
            SavedWeatherRow(weather)
        }
    )
}

@Composable
fun SavedWeatherRow(weather: WeatherBody) {
    var isExpanded by remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<HomeViewModel>()
    val weatherDetailsList by viewModel.weatherDetailsList.collectAsState()

    val data = weatherDetailsList.firstOrNull { it.locationId == weather.id }

    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomSize.S_80DP.size)
                .background(Color.White)
                .padding(top = CustomSize.S_10DP.size, bottom = CustomSize.S_10DP.size)
                .clickable {
                    isExpanded = !isExpanded
                }

        ) {
            val (placeName, region, weatherIcon, weatherTemp, updatedOn, divider) = createRefs()

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .constrainAs(placeName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                text = weather.location.name,
                style = TextStyle(
                    fontSize = CustomFontSize.FS_24SP.fontSize,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily
                )
            )

            Text(
                text = weather.location.regionName,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .constrainAs(region) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(placeName.start)
                    },
                maxLines = 1,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontWeight = FontWeight.W300
                )
            )

            AsyncImage(
                model = "https:${weather.current.condition.icon}",
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .constrainAs(weatherIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "${weather.current.tempC}°c",
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .constrainAs(weatherTemp) {
                        top.linkTo(parent.top)
                        end.linkTo(weatherIcon.start)
                    },
                maxLines = 1,
                style = TextStyle(
                    fontSize = CustomFontSize.FS_24SP.fontSize, fontWeight = FontWeight.W300
                )
            )

            Text(
                text = weather.current.lastUpdated,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .constrainAs(updatedOn) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.End,
                maxLines = 1,
                color = Color.Gray,
                style = TextStyle(
                    fontSize = CustomFontSize.FS_12SP.fontSize
                )
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(divider) {
                        bottom.linkTo(parent.bottom)
                    },
                thickness = CustomSize.S_0_3DP.size
            )
        }

        if (isExpanded) {
            Column {
                data?.let {
                    DetailsWeatherView(data)
                } ?: viewModel.getAstronomyData(
                    weatherData = weather.current,
                    locationId = weather.id,
                    latlon = "${weather.location.lat},${weather.location.lon}",
                    date = "2022-09-01"
                )
            }
        }
    }
}

@Composable
fun EmptyWeatherState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            painter = painterResource(id = R.drawable.weather_empty_state_icon),
            contentDescription = null,
            tint = colorResource(R.color.icon_color)
        )

        Text(
            modifier = Modifier
                .width(220.dp),
            textAlign = TextAlign.Center,
            text = "Search for a city or US/UK zip to check the weather",
            style = TextStyle(
                color = colorResource(R.color.icon_color)
            )
        )
    }
}


//@Preview("")
//@Composable
//fun SavedSearchesContainerPreview() {
//    SavedSearchesContainer()
//}