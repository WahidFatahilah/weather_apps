package com.wahid.weatherapplication.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wahid.weatherapplication.data.DataOrException
import com.wahid.weatherapplication.model.Weather
import com.wahid.weatherapplication.navigation.WeatherScreens
import com.wahid.weatherapplication.utils.formatDate
import com.wahid.weatherapplication.widgets.HumidityWindPressureRow
import com.wahid.weatherapplication.widgets.WeatherAppBar
import com.wahid.weatherapplication.widgets.WeatherStateImage
import com.wahid.weatherapplication.components.PasswordInput
import com.wahid.weatherapplication.components.ShowCityWeather
import com.wahid.weatherapplication.components.ShowWeather

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    val defaultCity = "Singapore"
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value
    val cityNewYork = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "New York")
    }.value

    val citySingapore = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Singapore")
    }.value

    val cityMumbai = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Mumbai")
    }.value

    val cityDelhi = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Delhi")
    }.value

    val citySydney = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Sydney")
    }.value

    val cityMelbourne = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Melbourne")
    }.value

    if (listOf(cityNewYork, citySingapore, cityMumbai, cityDelhi, citySydney, cityMelbourne).any { it.loading == true }) {
        CircularProgressIndicator()
    }else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!,
            weatherNewYork = cityNewYork.data!!,
            weatherSingapore = citySingapore.data!!,
            weatherMumbai = cityMumbai.data!!,
            weatherDelhi = cityDelhi.data!!,
            weatherSydney = citySydney.data!!,
            weatherMelbourne = cityMelbourne.data!!,
            navController = navController,
        )
    } else (
            Column() {
                Text(text = "OFFLINE MODE")
                Button(onClick = { navController.navigate(WeatherScreens.MainScreen.name+"/$defaultCity") }) {
                    Text(text = "Refresh")
                }
            }
            )
}

@Composable
fun MainScaffold(weather : Weather,
                 weatherNewYork : Weather,
                 weatherSingapore : Weather,
                 weatherMumbai : Weather,
                 weatherDelhi : Weather,
                 weatherSydney : Weather,
                 weatherMelbourne : Weather,
                 navController: NavController, ) {


    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ",${weather.city.country}",
            navController = navController,
            onAddActionClicked = {navController.navigate(WeatherScreens.SearchScreen.name) },
            elevation = 5.dp,
        )
    }) {
        weather.list[0]
        weather.list[0].weather[0].icon
        var imageUrl = "https://wahidftesting.000webhostapp.com/lottiefiles/${weather.list[0].weather[0].icon}.json"
        val defaultCity = "Singapore"
        var kelvin = weather.list[0].temp.eve.toInt()
        var celcius : Int = kelvin - 273

        ShowWeather(
            weather,
            imageUrl,
            celcius,
            navController,
            defaultCity,
            weatherNewYork,
            weatherSingapore,
            weatherMumbai,
            weatherDelhi,
            weatherSydney,
            weatherMelbourne
        )




    }
}


