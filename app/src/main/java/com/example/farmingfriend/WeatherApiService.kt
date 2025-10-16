package com.example.farmingfriend

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

data class WeatherResponse(
    val main: Main,
    val name: String,
    val weather: List<WeatherInfo>,
    val clouds: Clouds
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Clouds(
    val all: Int
)

data class WeatherInfo(
    val description: String
)

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>
}






