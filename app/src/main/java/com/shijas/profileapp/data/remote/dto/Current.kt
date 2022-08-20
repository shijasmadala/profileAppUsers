package com.shijas.profileapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.shijas.profileapp.domain.model.WeatherModel

@JsonClass(generateAdapter = true)
@Parcelize
data class Current(
    @Json(name = "clouds")
    val clouds: Int?,
    @Json(name = "dew_point")
    val dewPoint: Double?,
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "feels_like")
    val feelsLike: Double?,
    @Json(name = "humidity")
    val humidity: Int?,
    @Json(name = "pressure")
    val pressure: Int?,
    @Json(name = "sunrise")
    val sunrise: Int?,
    @Json(name = "sunset")
    val sunset: Int?,
    @Json(name = "temp")
    val temp: Double?,
    @Json(name = "uvi")
    val uvi: Double?,
    @Json(name = "visibility")
    val visibility: Int?,
    @Json(name = "weather")
    val weather: List<Weather>?,
    @Json(name = "wind_deg")
    val windDeg: Int?,
    @Json(name = "wind_gust")
    val windGust: Double?,
    @Json(name = "wind_speed")
    val windSpeed: Double?
) : Parcelable{
    fun toWeatherModel(): WeatherModel{
        return WeatherModel(
            humidity = humidity ?:0, temp = temp ?:0.0, windSpeed = windSpeed ?:0.0, weatherType = weather!![0].description ?:""
        )
    }
}