package com.example.lyricsapi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface LyricsApiService {
    @GET("{artist}/{title}")
    suspend fun getLyrics(
        @Path("artist") artist: String,
        @Path("title") title: String
    ): Response<LyricsResponse>
}