package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistNameTranslation(
    @Json(name = "artist_name_translation")
    val artistNameTranslation: Data = Data()
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Data(
        val language: String? = null,
        val translation: String? = null
    ) : Parcelable
}