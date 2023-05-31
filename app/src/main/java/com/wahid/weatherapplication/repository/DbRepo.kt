package com.wahid.weatherapplication.repository

import com.wahid.weatherapplication.data.local.WeatherDao
import com.wahid.weatherapplication.data.local.WeatherTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getCity(): Flow<List<WeatherTable>> = weatherDao.getFavorites()
    suspend fun insertCity(favorite: WeatherTable) = weatherDao.insertFavorite(favorite)

    fun getTemp(): Flow<List<WeatherTable>> = weatherDao.getFavorites()
    suspend fun insertTemp(favorite: WeatherTable) = weatherDao.insertFavorite(favorite)



}