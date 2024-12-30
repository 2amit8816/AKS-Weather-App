package com.example.aksweatherapp.ui.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksweatherapp.R
import com.example.aksweatherapp.common.Utility
import com.example.aksweatherapp.data.dto.CurrentWeather
import com.example.aksweatherapp.ui.core.CustomFontSize
import com.example.aksweatherapp.ui.core.CustomSize
import com.example.aksweatherapp.ui.model.WeatherDetails

@Composable
fun DetailsWeatherView(weatherDetails: CurrentWeather, timeZone: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .background(colorResource(R.color.details_card_background))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(CustomSize.S_12DP.size)
                        .fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(CustomSize.S_20DP.size),
                            painter = painterResource(id = R.drawable.rainy_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.icon_color)
                        )

                        Text(
                            modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                            text = "Precipitation",
                            style = TextStyle(color = colorResource(R.color.icon_color))
                        )
                    }

                    Spacer(modifier = Modifier.height(CustomSize.S_6DP.size))

                    Text(
                        text = "${weatherDetails.current?.precipitation} mm",
                        style = TextStyle(
                            color = colorResource(R.color.black),
                            fontSize = CustomFontSize.FS_16SP.fontSize
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .background(colorResource(R.color.details_card_background))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(CustomSize.S_12DP.size)
                        .fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(CustomSize.S_20DP.size),
                            painter = painterResource(id = R.drawable.uv_index_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.icon_color)
                        )

                        Text(
                            modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                            text = "UV index",
                            style = TextStyle(color = colorResource(R.color.icon_color))
                        )
                    }

                    Spacer(modifier = Modifier.height(CustomSize.S_6DP.size))

                    Text(
                        text = "${weatherDetails.daily?.uvIndexMax?.get(0)}",
                        style = TextStyle(
                            color = colorResource(R.color.black),
                            fontSize = CustomFontSize.FS_16SP.fontSize
                        )
                    )
                }
            }

        }


        Spacer(modifier = Modifier.width(10.dp))

        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .background(colorResource(R.color.details_card_background))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(CustomSize.S_12DP.size),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier

                    ) {
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(CustomSize.S_20DP.size),
                                painter = painterResource(id = R.drawable.air_icon),
                                contentDescription = null,
                                tint = colorResource(R.color.icon_color)
                            )

                            Text(
                                modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                                text = "Wind",
                                style = TextStyle(color = colorResource(R.color.icon_color))
                            )
                        }

                        Spacer(modifier = Modifier.height(CustomSize.S_6DP.size))

                        Text(
                            text = "${weatherDetails.current?.windSpeed10m} kph",
                            style = TextStyle(
                                color = colorResource(R.color.black),
                                fontSize = CustomFontSize.FS_16SP.fontSize
                            )
                        )
                    }

                    Text(
                        text = "N",
                        style = TextStyle(
                            color = colorResource(R.color.black),
                            fontSize = CustomFontSize.FS_32SP.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .background(colorResource(R.color.details_card_background)),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(CustomSize.S_12DP.size)
                        .fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(CustomSize.S_20DP.size),
                            painter = painterResource(id = R.drawable.sunny_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.icon_color)
                        )

                        Text(
                            modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                            text = "Sun",
                            style = TextStyle(color = colorResource(R.color.icon_color))
                        )
                    }

                    Spacer(modifier = Modifier.height(CustomSize.S_6DP.size))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.size(CustomSize.S_16DP.size),
                                painter = painterResource(id = R.drawable.sun_rise),
                                contentDescription = null,
                            )

                            Text(
                                modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                                text = Utility.getFormattedDate(
                                    weatherDetails.daily?.sunrise?.get(0)!!.toString(),
                                    timeZone
                                ),
                                style = TextStyle(fontSize = CustomFontSize.FS_12SP.fontSize)
                            )
                        }

                        Row {
                            Icon(
                                modifier = Modifier.size(CustomSize.S_16DP.size),
                                painter = painterResource(id = R.drawable.sun_set),
                                contentDescription = null,
                            )

                            Text(
                                modifier = Modifier.padding(start = CustomSize.S_4DP.size),
                                text = Utility.getFormattedDate(
                                    weatherDetails.daily?.sunset?.get(0)!!.toString(),
                                    timeZone
                                ),
                                style = TextStyle(fontSize = CustomFontSize.FS_12SP.fontSize)
                            )
                        }
                    }
                }
            }
        }
    }
}