package com.wahid.weatherapplication.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.wahid.weatherapplication.model.WeatherItem

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.padding(4.dp)) {

            Text(text = "${weather.humidity}%")

        }
        Row(modifier = Modifier.padding(4.dp)) {

            Text(text = "${weather.pressure}psi")

        }
        Row(modifier = Modifier.padding(4.dp)) {

            Text(text = "${weather.speed}mph")

        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    val retrySignal = rememberLottieRetrySignal()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url(imageUrl), onRetry = { failCount, exception ->
            retrySignal.awaitRetry()
            true
        })
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}
