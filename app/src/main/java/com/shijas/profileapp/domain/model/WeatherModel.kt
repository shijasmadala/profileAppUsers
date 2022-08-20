package com.shijas.profileapp.domain.model

data class WeatherModel(
    val weatherType: String,
    val temp: Double,
    val humidity: Int,
    val windSpeed: Double
)
