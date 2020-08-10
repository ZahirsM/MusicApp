package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistAlias(
    @Json(name = "artist_alias")
    val artistAlias: String = ""
) : Parcelable