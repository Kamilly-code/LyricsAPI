package com.example.lyricsapi

import com.example.lyricsapi.data.LastFmResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService {
    @GET("?method=track.search&format=json")
    suspend fun searchTrack(
        @Query("track") track: String,
        @Query("api_key") apiKey: String
    ): Response<LastFmResponse>
}