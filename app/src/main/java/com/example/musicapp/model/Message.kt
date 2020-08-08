package com.example.musicapp.model


import android.os.Parcelable
import com.example.musicapp.model.Body
import com.example.musicapp.model.Header
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Message(
    val header: Header = Header(),
    val body: Body = Body()
) : Parcelable