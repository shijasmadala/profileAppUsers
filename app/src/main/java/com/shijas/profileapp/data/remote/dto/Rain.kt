package com.shijas.profileapp.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Rain(
    @Json(name = "1h")
    val h: Double?
) : Parcelable