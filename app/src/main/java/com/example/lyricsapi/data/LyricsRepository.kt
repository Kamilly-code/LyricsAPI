package com.example.lyricsapi.data

import com.example.lyricsapi.ui.network.LyricsApiService
import retrofit2.HttpException
import java.io.IOException

class LyricsRepository(private val apiService: LyricsApiService) {
    suspend fun getLyrics(artist: String, title: String): Result<String> {
        return try {
            val response = apiService.getLyrics(artist, title)
            if (response.isSuccessful) {
                response.body()?.lyrics?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Letra no encontrada"))
            } else {
                Result.failure(Exception("Error : ${response.code()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        } catch (e: HttpException) {
            Result.failure(Exception("Error HTTP: ${e.message}"))
        }
    }
}