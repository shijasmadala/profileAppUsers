package com.shijas.profileapp.domain.repository.remote

import com.shijas.beeonetest.common.Resource
import com.shijas.profileapp.domain.model.WeatherModel
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather() : Flow<Resource<WeatherModel>>

}