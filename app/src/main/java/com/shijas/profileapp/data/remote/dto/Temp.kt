package com.shijas.profileapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Temp(
    @Json(name = "day")
    val day: Double?,
    @Json(name = "eve")
    val eve: Double?,
    @Json(name = "max")
    val max: Double?,
    @Json(name = "min")
    val min: Double?,
    @Json(name = "morn")
    val morn: Double?,
    @Json(name = "night")
    val night: Double?
) : Parcelable