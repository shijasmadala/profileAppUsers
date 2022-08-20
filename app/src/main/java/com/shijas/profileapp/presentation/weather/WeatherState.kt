package com.shijas.profileapp.presentation.weather

import com.shijas.profileapp.domain.model.WeatherModel
import com.shijas.profileapp.presentation.add.AddUserState

sealed class WeatherState{
    data class Success(
        val weather: WeatherModel,

        ) : WeatherState()


    data class Error(val message: String) : WeatherState()
    object Loading : WeatherState()
    object Empty : WeatherState()
}
