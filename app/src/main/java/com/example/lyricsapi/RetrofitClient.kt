package com.example.lyricsapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val BASE_URL = "https://api.lyrics.ovh/"

    val lyricsService: LyricsApiService by lazy {
        getRetrofit().create(LyricsApiService::class.java)
    }

    private fun getRetrofit():Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}