package com.shijas.profileapp.data.remote.source

import com.shijas.profileapp.data.remote.dto.WeatherResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface WeatherService {
    @GET("onecall?lat=12.9082847623315&lon=77.65197822993314&units=imperial&appid=b143bb707b2ee117e62649b358207d3e")
    suspend fun getWeather() : ApiResponse<WeatherResponseDto>

}