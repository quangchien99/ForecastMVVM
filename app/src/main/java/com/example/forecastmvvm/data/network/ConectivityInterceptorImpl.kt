package com.example.forecastmvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.forecastmvvm.data.internal.NoConectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            throw NoConectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}