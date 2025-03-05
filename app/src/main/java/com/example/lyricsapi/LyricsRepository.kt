package com.example.lyricsapi

class LyricsRepository(private val apiService: LyricsApiService) {
    suspend fun getLyrics(artist: String, title: String): String? {
        val response = apiService.getLyrics(artist, title)
        return if (response.isSuccessful) {
            response.body()?.lyrics
        } else {
            null
        }
    }
}