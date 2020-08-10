package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicResponse(
    val message: Message = Message()
) : Parcelable