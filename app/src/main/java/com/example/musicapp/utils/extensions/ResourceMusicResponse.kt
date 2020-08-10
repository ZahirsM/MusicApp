package com.example.musicapp.utils

import com.example.musicapp.data.model.Body
import retrofit2.Response

fun Response<Body>.successWithData() =
    isSuccessful && body() != null && !body()?.data.isNullOrEmpty()

fun Response<Body>.successNoData() =
    isSuccessful && body() != null && body()?.data.isNullOrEmpty()



