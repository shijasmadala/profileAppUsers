package com.shijas.profileapp.data.repository.remote

import com.shijas.beeonetest.common.Constants
import com.shijas.beeonetest.common.Resource
import com.shijas.profileapp.data.remote.source.WeatherService
import com.shijas.profileapp.domain.model.WeatherModel
import com.shijas.profileapp.domain.repository.remote.WeatherRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
): WeatherRepository {
    override fun getWeather(): Flow<Resource<WeatherModel>> =flow{
        emit(Resource.Loading)
        weatherService.getWeather().suspendOnSuccess {

            val weatherResp = this.data
            if (weatherResp.current != null){
                val showWeather = weatherResp.current.toWeatherModel()
                emit(Resource.Success(showWeather))
            } else {
                emit(Resource.Error("No Data Found"))
            }


        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            val ex = this.message
            emit(Resource.Error(ex?:""))
        }
    }


}