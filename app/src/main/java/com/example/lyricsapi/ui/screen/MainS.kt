package com.example.lyricsapi.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lyricsapi.LyricsViewModel
import com.example.lyricsapi.LyricsViewModelFactory
import com.example.lyricsapi.R
import com.example.lyricsapi.RetrofitClient
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainS() {
    var songName by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    val coroutineScope = rememberCoroutineScope()

    val lyricsRepository = RetrofitClient.lyricsRepository
    val viewModel: LyricsViewModel = viewModel(factory = LyricsViewModelFactory(lyricsRepository))
    val lyrics by viewModel.lyrics.collectAsState()

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = artistName,
                    onValueChange = { newValue ->
                        artistName = newValue
                    },
                    label = { Text("Enter artist name") }
                )
                TextField(
                    value = songName,
                    onValueChange = { newValue ->
                        songName = newValue
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
                        viewModel.fetchLyrics(artistName, songName)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Get Lyrics")
                }
                lyrics?.let { lyricsText ->
                    Text(
                        text = lyricsText.trim().replace("\n\n", "\n"),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .verticalScroll(rememberScrollState())
                    )
                }

            }
        }
    )
}