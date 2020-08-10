package com.example.musicapp.data.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Message(
    val header: Header = Header(),
    val body: Body = Body()
) : Parcelable