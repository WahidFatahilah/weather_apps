package com.wahid.weatherapplication.data.local

import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "weather_table")
data class WeatherTable(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val id: String,

    @ColumnInfo(name = "temp")
    val city: String)
