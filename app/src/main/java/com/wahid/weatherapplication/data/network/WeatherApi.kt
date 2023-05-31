package com.wahid.weatherapplication.data.network

import com.wahid.weatherapplication.model.Weather
import com.wahid.weatherapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


//api service builder
@Singleton
interface WeatherApi {
    @GET(value ="data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("unit") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY
    ) : Weather
}