package com.example.a6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val audioFiles = listOf(
            AudioFile("Song A", "Artist Z", 210, "Pop"),
            AudioFile("Song B", "Artist Y", 180, "Rock"),
            AudioFile("Song C", "Artist Z", 240, "Jazz"),
            AudioFile("Song D", "Artist X", 300, "Classical")
        )

        val sortedAudioFiles = audioFiles.sortedBy { it.artist }

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(100.dp))

                    Text(text = "Информация об аудиофайлах", modifier = Modifier.padding(16.dp))

                    sortedAudioFiles.forEach { file ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Название: ${file.title}")
                            Text(text = "Исполнитель: ${file.artist}")
                            Text(text = "Длительность: ${file.formattedDuration()}")
                            Text(text = "Жанр: ${file.genre}")
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
