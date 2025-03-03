package com.example.lyricsapi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun fetchLyrics(artist: String, title: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitClient.lyricsService.getLyrics(artist, title)
            if (response.isSuccessful) {
                response.body()?.lyrics
            } else {
                null
            }
        } catch (e: HttpException) {
            null
        }
    }
}