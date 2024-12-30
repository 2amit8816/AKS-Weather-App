@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.aksweatherapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aksweatherapp.R
import com.example.aksweatherapp.common.UiState
import com.example.aksweatherapp.data.dto.Location
import com.example.aksweatherapp.ui.core.CustomSize

@Composable
fun SearchBox(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val searchResults by viewModel.locationsList.collectAsState()
    val input = viewModel.inputText.collectAsState()
    val currentWeather = viewModel.currentWeather.collectAsState()

    Column(modifier = modifier.padding(top = CustomSize.S_10DP.size)) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomSize.S_40DP.size)
                .background(Color.White),
            value = input.value,
            onValueChange = {
                viewModel.updateInput(it)
                viewModel.getLocationSuggetionList()
            },
            maxLines = 1,
        ) {
            TextFieldDefaults.DecorationBox(
                placeholder = {
                    Text(
                        text = "City, Region or US/UK zip code",
                        color = Color.Gray.copy(alpha = 0.5f),
                    )
                },
                value = input.value,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                shape = RoundedCornerShape(CustomSize.S_10DP.size),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.clearSearch()
                        },
                        tint = colorResource(R.color.icon_color)
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        "",
                        tint = colorResource(R.color.icon_color)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.White,
                    focusedContainerColor = colorResource(R.color.text_background),
                    unfocusedContainerColor = colorResource(R.color.text_background),
                ),
                contentPadding = PaddingValues(CustomSize.S_0DP.size),
            )
        }

        SearchResult(searchResults) { location ->
            location?.let {
                viewModel.getWeatherByLatLonFrom(it)
            }
        }
    }
}

@Composable
fun SearchResult(
    searchResults: UiState<List<Location?>>,
    onLocationSelected: (Location?) -> Unit
) {
    if (searchResults is UiState.Success && searchResults.uiData.isNotEmpty()) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.near_me_icon),
                contentDescription = "Location Icon",
                modifier = Modifier.padding(CustomSize.S_12DP.size),
                tint = Color.Blue
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CustomSize.S_12DP.size),
                text = "Current Locations",
                maxLines = 1,
                color = Color.Blue
            )
        }
        LazyColumn {
            itemsIndexed(searchResults.uiData) { _, location ->
                Divider(color = Color.LightGray, thickness = (CustomSize.S_0_5DP.size))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomSize.S_10DP.size)
                        .clickable { onLocationSelected(location) },
                    text = location?.name + ", " + location?.region + ", " + location?.country,
                    maxLines = 1
                )
            }
        }
    }
}