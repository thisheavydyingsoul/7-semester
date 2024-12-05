package com.example.a6

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaMetadataRetriever
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

data class AudioFile(
    val title: String,
    val artist: String,
    val duration: Int, // Duration in milliseconds
    val genre: String
)

class MainActivity : AppCompatActivity() {

    private lateinit var scrollView: ScrollView
    private lateinit var linearLayout: LinearLayout

    companion object {
        private const val REQUEST_CODE_PERMISSION = 1 // Код запроса разрешения
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создаем ScrollView и LinearLayout программно
        scrollView = ScrollView(this)
        linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        scrollView.addView(linearLayout)
        setContentView(scrollView)

        checkPermission();
    }

    private fun checkPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO // Для Android 13+
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE // Для более ранних версий
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CODE_PERMISSION)
        } else {
            displayAudioFiles() // Разрешение уже предоставлено
        }
    }

    // Обработка результата запроса разрешения
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayAudioFiles() // Разрешение предоставлено
            } else {
                Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAudioFiles(): List<AudioFile> {
        val audioFiles = mutableListOf<AudioFile>()
        val musicDir = File(Environment.getExternalStorageDirectory(), "Music")

        if (musicDir.exists() && musicDir.isDirectory) {
            musicDir.listFiles()?.forEach { file ->
                if (file.extension.equals("mp3", ignoreCase = true) ||
                    file.extension.equals("wav", ignoreCase = true)) {

                    val retriever = MediaMetadataRetriever()
                    retriever.setDataSource(file.absolutePath)

                    val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown"
                    val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Unknown"
                    val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toInt() ?: 0
                    val genre = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE) ?: "Unknown"

                    audioFiles.add(AudioFile(title, artist, duration, genre))
                    retriever.release()
                }
            }
        }

        return audioFiles.sortedBy { it.artist }
    }

    private fun formatDuration(durationInMillis: Int): String {
        val minutes = (durationInMillis / 1000) / 60
        val seconds = (durationInMillis / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun displayAudioFiles() {
        val audioFiles = getAudioFiles()

        for (audioFile in audioFiles) {
            val textView = TextView(this).apply {
                text = "Название: ${audioFile.title}, Исполнитель: ${audioFile.artist}, Длительность: ${formatDuration(audioFile.duration)}, Жанр: ${audioFile.genre}"
                textSize = 16f // Устанавливаем размер текста
                setPadding(16, 16, 16, 16) // Устанавливаем отступы для текста
            }
            linearLayout.addView(textView)
        }
    }
}