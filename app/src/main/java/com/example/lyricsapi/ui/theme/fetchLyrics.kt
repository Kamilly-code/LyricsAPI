package com.example.lyricsapi.ui.theme

import com.example.lyricsapi.ui.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun fetchLyrics(artist: String, title: String): Result<String> {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitClient.lyricsService.getLyrics(artist, title)
            if (response.isSuccessful) {
                val lyrics = response.body()?.lyrics
                if (!lyrics.isNullOrEmpty()) {
                    Result.success(lyrics)
                } else {
                    Result.failure(Exception("Letra no encontrada"))
                }
            } else if (response.code() == 404) {
                Result.failure(Exception("Artista o canción no encontrados"))
            } else {
                Result.failure(Exception("Error en la API: ${response.code()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        } catch (e: HttpException) {
            Result.failure(Exception("Error HTTP: ${e.message}"))
        }
    }
}