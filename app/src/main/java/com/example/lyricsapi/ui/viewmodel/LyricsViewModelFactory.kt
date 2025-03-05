package com.example.lyricsapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lyricsapi.data.LyricsRepository

class LyricsViewModelFactory (private val repository: LyricsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LyricsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LyricsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}