package com.example.lyricsapi.data

data class LastFmResponse(val results: TrackMatches)

data class TrackMatches(val trackmatches: TrackList)

data class TrackList(val track: List<Track>)

data class Track(val artist: String, val name: String)
