package com.wahid.weatherapplication.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wahid.weatherapplication.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController) {

    val defaultCity = "Singapore"
    val scales = remember {

        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scales.animateTo(targetValue = 0.9f,
            animationSpec = tween(800,
                easing = { OvershootInterpolator(8f).getInterpolation(it)}))

        delay(3000L)

            navController.navigate(WeatherScreens.MainScreen.name+"/$defaultCity")
    })

    androidx.compose.material.Surface(modifier = Modifier
        .padding(15.dp)
        .scale(scales.value)
        .size(330.dp), shape = CircleShape, color = Color.White,
        border = BorderStroke(
            width = 2.dp,
            color = Color.LightGray
        )
    ) {
        Column(modifier = Modifier.padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        }
    }
}
