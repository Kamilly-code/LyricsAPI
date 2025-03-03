package com.example.lyricsapi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun fetchArtistSuggestions(track: String): List<String>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitClient.lastFmService.searchTrack(track, "YOUR_API_KEY")
            if (response.isSuccessful) {
                response.body()?.results?.trackmatches?.track?.map { it.artist }
            } else {
                null
            }
        } catch (e: HttpException) {
            null
        }
    }
}