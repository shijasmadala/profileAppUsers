package com.shijas.profileapp.presentation.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.shijas.profileapp.R
import com.shijas.profileapp.databinding.FragmentAddUserBinding
import com.shijas.profileapp.databinding.FragmentWeatherBinding
import com.shijas.profileapp.domain.model.WeatherModel
import com.shijas.profileapp.presentation.add.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel by viewModels<WeatherViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)
        observeWeather()
    }

    private fun observeWeather(){
        with(binding){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getWeather.collect {
                    when (it) {
                        is WeatherState.Loading -> {

                            progressBar.isVisible = true
                            layout1.isVisible=false

                        }
                        is WeatherState.Success -> {

                            progressBar.isVisible = false
                            layout1.isVisible=true
                            setWeatherItem(it.weather)
                        }
                        is WeatherState.Error -> {
                            progressBar.isVisible = false
                            layout1.isVisible=true
                            findNavController().navigateUp()
                            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT)
                        }
                        else -> {
                            Unit
                        }
                    }
                }
            }
        }
        }
    }

    private fun setWeatherItem(weatherModel: WeatherModel) {
        with(binding){
            temp.text =weatherModel.temp.toString() +" f"
            windHumidity.text = weatherModel.humidity.toString()
            windType.text = weatherModel.weatherType.toString()
            windSpeed.text = weatherModel.windSpeed.toString()
        }
    }
}