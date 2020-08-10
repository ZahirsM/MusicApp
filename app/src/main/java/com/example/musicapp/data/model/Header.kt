package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Header(
    @Json(name = "status_code")
    val statusCode: Int? = null,
    @Json(name = "execute_time")
    val executeTime: Double? = null
) : Parcelable