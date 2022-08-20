package com.shijas.profileapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Minutely(
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "precipitation")
    val precipitation: Double?
) : Parcelable