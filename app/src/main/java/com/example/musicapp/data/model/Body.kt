package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Body(
    @Json(name = "artist_list")
    val data: List<Artist> = listOf()
) : Parcelable