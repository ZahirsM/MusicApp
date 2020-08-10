package com.example.musicapp.data.api

import com.example.musicapp.data.repository.MusicRepo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val interceptor : Interceptor by lazy {
        Interceptor { chain ->
            val url: HttpUrl = chain.request().url.newBuilder()
                .addQueryParameter("apikey", MusicRepo.API_KEY)
                .build()
            val request : Request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    private val client by lazy {
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
            .let { OkHttpClient.Builder().addInterceptor(interceptor).build() }
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.musixmatch.com/ws/1.1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val musicService: MusicService
        get() = retrofit.create(MusicService::class.java)
}
