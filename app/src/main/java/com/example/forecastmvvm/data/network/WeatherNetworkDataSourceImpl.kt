package com.example.forecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastmvvm.data.APIWeatherService
import com.example.forecastmvvm.data.internal.NoConnectivityException
import com.example.forecastmvvm.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(private val apiWeatherService: APIWeatherService) :
    WeatherNetworkDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = apiWeatherService
                .getCurrentWeather(location)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e: NoConnectivityException) {
            Log.e("qcpTag", "No internet connection")
        }
    }
}