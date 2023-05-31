package com.wahid.weatherapplication.repository

import android.util.Log
import com.wahid.weatherapplication.data.DataOrException
import com.wahid.weatherapplication.model.Weather
import com.wahid.weatherapplication.data.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi) {
    suspend fun getWeather(cityQuery: String):DataOrException<Weather, Boolean, Exception>
    {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e : Exception){
            Log.d("REX", "Getweather : $e")
            return DataOrException(e = e)
        }
          return DataOrException(data = response)
    }

}