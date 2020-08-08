package com.example.musicapp

import com.example.musicapp.model.MusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("chart.artists.get")
    suspend fun getChartArtists(
        @Query("apikey") apiKey: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("country") country: String
    ) : Response<MusicResponse>
}