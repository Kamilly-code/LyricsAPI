package com.example.lyricsapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lyricsapi.data.LyricsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LyricsViewModel(private val repository: LyricsRepository) : ViewModel() {
    private val _lyrics = MutableStateFlow<String?>(null)
    val lyrics: StateFlow<String?> get() = _lyrics

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun fetchLyrics(artist: String, title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getLyrics(artist, title)
            if (result.isSuccess) {
                _lyrics.value = result.getOrNull()
                _errorMessage.value = null
            } else {
                _lyrics.value = null
                _errorMessage.value = result.exceptionOrNull()?.message
            }
            _isLoading.value = false
        }
    }
}