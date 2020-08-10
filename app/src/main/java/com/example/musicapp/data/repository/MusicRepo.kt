package com.example.musicapp.data.repository

import com.example.musicapp.data.api.RetrofitInstance.musicService
import com.example.musicapp.data.model.MusicResponse
import okhttp3.Response

class MusicRepo() {
    companion object {
        const val API_KEY = "70dcd95d3e8b72607a704b7a8ce67604"
    }

    suspend fun getArtists(
        page: Int,
        pageSize: Int,
        country: String
    ) = musicService.getChartArtists(
        apiKey = API_KEY,
        page = page,
        pageSize = pageSize,
        country = country
    )
}