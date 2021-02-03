package com.example.forecastmvvm.ui.weather.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forecastmvvm.R
import com.example.forecastmvvm.data.APIWeatherService
import kotlinx.android.synthetic.main.today_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodayWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            TodayWeatherFragment()
    }

    private lateinit var viewModel: TodayWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayWeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = APIWeatherService()
        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = apiService.getCurrentWeather("New York").await()
            println(apiService.getCurrentWeather("London").isCompleted)
            println(currentWeatherResponse.currentWeatherEntry.toString())
            textView.text = currentWeatherResponse.currentWeatherEntry.toString()
        }
    }

}