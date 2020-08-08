package com.example.musicapp.model


import android.os.Parcelable
import com.example.musicapp.model.Artist
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistCredits(
    @Json(name = "artist_list")
    val artistList: List<Artist> = listOf()
) : Parcelable