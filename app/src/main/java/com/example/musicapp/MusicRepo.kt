package com.example.musicapp

class MusicRepo(private val musicServiceImpl: MusicServiceImpl) {
    companion object {
        const val API_KEY = "b370b0c9c8089410a3b498c9ec2c02a8"
    }

    suspend fun getArtists(
        page: Int,
        pageSize: Int,
        country: String
    ) = musicServiceImpl.getChartArtists(
        apiKey = API_KEY,
        page = page,
        pageSize = pageSize,
        country = country
    )
}