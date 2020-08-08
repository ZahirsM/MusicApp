package com.example.musicapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val client by lazy {
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
            .let { OkHttpClient.Builder().addInterceptor(it).build() }
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.musixmatch.com/ws/1.1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val musicService
        get() = retrofit.create(MusicService::class.java)
}