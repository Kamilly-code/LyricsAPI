package com.example.lyricsapi.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lyricsapi.ui.viewmodel.LyricsViewModel
import com.example.lyricsapi.ui.viewmodel.LyricsViewModelFactory
import com.example.lyricsapi.R
import com.example.lyricsapi.ui.network.RetrofitClient
import androidx.lifecycle.viewmodel.compose.viewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainS() {
    var songName by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var suggestions by remember { mutableStateOf(listOf<String>()) }

    val lyricsRepository = RetrofitClient.lyricsRepository
    val viewModel: LyricsViewModel = viewModel(factory = LyricsViewModelFactory(lyricsRepository))
    val lyrics by viewModel.lyrics.collectAsState()

    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

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

        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        value = artistName,
                        onValueChange = { newValue ->
                            artistName = newValue
                        },
                        label = { Text("Cantante") },

                    )
                    TextField(
                        value = songName,
                        onValueChange = { newValue ->
                            songName = newValue
                        },
                        label = { Text("Canción") }
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                    ) {
                        items(suggestions) { suggestion ->
                            Text(
                                text = suggestion,
                                color = Color.White,
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
                            viewModel.fetchLyrics(artistName, songName)
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text("Letra de la canción", color = Color.White)
                    }
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        lyrics?.let { lyricsText ->
                            Text(
                                text = lyricsText.trim().replace("\n\n", "\n"),
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .verticalScroll(rememberScrollState())
                            )
                        }
                        errorMessage?.let { errorText ->
                            Text(
                                text = errorText,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}