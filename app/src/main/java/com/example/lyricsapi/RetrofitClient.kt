package com.example.lyricsapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val LYRICS_BASE_URL = "https://api.lyrics.ovh/v1/"
    private const val LAST_FM_BASE_URL = "http://ws.audioscrobbler.com/2.0/"

    val lyricsService: LyricsApiService by lazy {
        getRetrofit(LYRICS_BASE_URL).create(LyricsApiService::class.java)
    }

    val lastFmService: LastFmApiService by lazy {
        getRetrofit(LAST_FM_BASE_URL).create(LastFmApiService::class.java)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}