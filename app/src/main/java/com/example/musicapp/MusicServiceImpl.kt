package com.example.musicapp

import com.example.musicapp.model.MusicResponse
import retrofit2.Response

class MusicServiceImpl : MusicService {

    override suspend fun getChartArtists(
        apiKey: String,
        page: Int,
        pageSize: Int,
        country: String
    ) = RetrofitInstance.musicService.getChartArtists(
        apiKey = apiKey,
        page = page,
        pageSize = pageSize,
        country = country
    )
}