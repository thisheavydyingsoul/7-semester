package com.example.a6

data class AudioFile(
    val title: String,
    val artist: String,
    val durationInSeconds: Int, // Длительность в секундах
    val genre: String
) {
    fun formattedDuration(): String {
        val minutes = durationInSeconds / 60
        val seconds = durationInSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
