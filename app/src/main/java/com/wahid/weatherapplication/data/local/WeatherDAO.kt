package com.wahid.weatherapplication.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * from weather_table")
    fun getFavorites(): Flow<List<WeatherTable>>

    @Query("SELECT * from weather_table where city =:city")
    suspend fun getFavById(city: String): WeatherTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: WeatherTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: WeatherTable)

    @Query("DELETE from weather_table")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: WeatherTable)


}