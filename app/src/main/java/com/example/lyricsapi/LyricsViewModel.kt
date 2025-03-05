package com.example.lyricsapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LyricsViewModel(private val repository : LyricsRepository) : ViewModel() {
    private val _lyrics = MutableStateFlow<String?>(null)
    val lyrics: MutableStateFlow<String?> get()= _lyrics


    fun fetchLyrics(artist: String, title: String) {
        viewModelScope.launch {
            _lyrics.value = repository.getLyrics(artist, title)
        }
    }
}