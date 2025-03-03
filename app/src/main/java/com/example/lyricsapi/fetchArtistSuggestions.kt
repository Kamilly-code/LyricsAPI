package com.example.lyricsapi

/*import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun fetchArtistSuggestions(track: String): List<String>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitClient.lastFmService.searchTrack(track, "7fe09dacd45d23881661aca6b66ae15f")
            if (response.isSuccessful) {
                response.body()?.results?.trackmatches?.track?.map { it.artist }
            } else {
                Log.e("fetchArtistSuggestions", "Response not successful: ${response.code()}")
                null
            }
        } catch (e: HttpException) {
            Log.e("fetchArtistSuggestions", "HttpException: ${e.message()}")
            null
        } catch (e: Exception) {
            Log.e("fetchArtistSuggestions", "Exception: ${e.message}")
            null
        }
    }
}*/

