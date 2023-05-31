package com.wahid.weatherapplication.ui.screens.main

import androidx.lifecycle.ViewModel
import com.wahid.weatherapplication.data.DataOrException
import com.wahid.weatherapplication.model.Weather
import com.wahid.weatherapplication.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    :ViewModel() {
        suspend fun getWeatherData(city: String) : DataOrException<Weather, Boolean,Exception>
        {
            return repository.getWeather(cityQuery = city)
        }

/*
        val data : MutableState<DataOrException<Weather, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception("")))

*/












    /*init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Yogyakarta")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)
            if (data.value.data.toString().isNotEmpty()) data.value.loading = false

        }

        Log.d("GET", "Get Weather : ${data.value.data.toString()}")

    }*/
}