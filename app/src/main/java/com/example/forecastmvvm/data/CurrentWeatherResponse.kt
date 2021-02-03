package com.example.forecastmvvm.data

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("Current")
    val current: CurrentWeatherEntry
)