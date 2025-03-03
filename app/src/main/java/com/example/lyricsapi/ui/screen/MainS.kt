package com.example.lyricsapi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lyricsapi.R
import com.example.lyricsapi.fetchLyrics
import kotlinx.coroutines.launch
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainS() {
    var songName by remember { mutableStateOf("") }
    var lyrics by remember { mutableStateOf<String?>(null) }
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = {}) {

                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = songName,
                    onValueChange = { newValue ->
                        songName = newValue
                        // Update suggestions based on the new value
                        suggestions = getSuggestions(newValue)
                    },
                    label = { Text("Enter song name") }
                )
                LazyColumn {
                    items(suggestions) { suggestion ->
                        Text(
                            text = suggestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    songName = suggestion
                                    suggestions = emptyList()
                                }
                        )
                    }
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            lyrics = fetchLyrics("Adele", songName)

                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Get Lyrics")
                }
                lyrics?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    )
}


fun getSuggestions(query: String): List<String> {
    // Replace with actual logic to get suggestions
    return listOf("Song 1", "Song 2", "Song 3").filter { it.contains(query, ignoreCase = true) }
}

