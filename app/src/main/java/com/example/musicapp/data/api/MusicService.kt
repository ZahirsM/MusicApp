package com.example.musicapp.data.api

import com.example.musicapp.data.model.MusicResponse
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