package com.shijas.profileapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.beeonetest.common.Resource
import com.shijas.profileapp.domain.repository.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _getWeather = MutableStateFlow<WeatherState>(WeatherState.Empty)
    val getWeather = _getWeather.asStateFlow()

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        weatherRepository.getWeather().collect{
            when(it){
                Resource.Loading -> {
                    _getWeather.emit(WeatherState.Loading)
                }
                is Resource.Success -> {
                    _getWeather.emit(WeatherState.Success(it.value))
                }
                is Resource.Error -> {
                    _getWeather.emit(WeatherState.Error(it.error))
                }
                else -> {Unit}
            }
        }
    }

}