package com.shijas.profileapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class WeatherResponseDto(
    @Json(name = "current")
    val current: Current?,
    @Json(name = "daily")
    val daily: List<Daily>?,
    @Json(name = "hourly")
    val hourly: List<Hourly>?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?,
    @Json(name = "minutely")
    val minutely: List<Minutely>?,
    @Json(name = "timezone")
    val timezone: String?,
    @Json(name = "timezone_offset")
    val timezoneOffset: Int?
) : Parcelable