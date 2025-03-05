package com.example.lyricsapi.ui.network

import com.example.lyricsapi.data.LyricsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.lyrics.ovh/"

    val lyricsService: LyricsApiService by lazy {
        getRetrofit().create(LyricsApiService::class.java)
    }

    val lyricsRepository: LyricsRepository by lazy {
        LyricsRepository(lyricsService)
    }

    private fun getRetrofit():Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}