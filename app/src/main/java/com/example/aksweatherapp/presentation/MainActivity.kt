package com.example.aksweatherapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aksweatherapp.ui.core.CustomFontSize
import com.example.aksweatherapp.ui.core.CustomSize
import com.example.aksweatherapp.ui.home.SavedSearchesContainer
import com.example.aksweatherapp.ui.home.SearchBox
import com.example.aksweatherapp.ui.theme.AKSWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AKSWeatherAppTheme {
                Scaffold() { _ ->
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(CustomSize.S_20DP.size)
    ) {
        val (topSpace, title, search, savedContainer) = createRefs()

        Spacer(modifier = Modifier
            .constrainAs(topSpace) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxHeight(0.1f))

        Text(
            text = "Weather App",
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(topSpace.bottom)
                    start.linkTo(parent.start)
                },
            style = TextStyle(
                fontSize = CustomFontSize.FS_36SP.fontSize, fontWeight = FontWeight.Bold
            )
        )

        SearchBox(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(search) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SavedSearchesContainer(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(bottom = CustomSize.S_10DP.size, top = CustomSize.S_10DP.size)
                .constrainAs(savedContainer) {
                    top.linkTo(search.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}