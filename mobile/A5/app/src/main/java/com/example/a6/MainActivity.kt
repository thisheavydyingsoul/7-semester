package com.example.a6

import android.content.Intent

import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private val colors = arrayOf(
        Color.rgb(255, 0, 0),
        Color.rgb(0, 255, 0),
        Color.rgb(0, 0, 255),
        Color.rgb(255, 255, 0),
        Color.rgb(0, 255, 255),
        Color.rgb(255, 0, 255),
        Color.rgb(211, 211, 211),
        Color.rgb(169, 169, 169),
        Color.rgb(255, 255, 255)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val title = TextView(this).apply {
            text = "Выберите цвет"
            textSize = 24f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        mainLayout.addView(title)

        val gridLayout = GridLayout(this).apply {
            rowCount = 3
            columnCount = 3
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        colors.forEach { color ->
            val button = ImageButton(this).apply {
                setBackgroundColor(color)
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0 // Занимает равную долю пространства
                    height = 200 // Высота кнопки в пикселях
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                setOnClickListener {
                    val intent = Intent(this@MainActivity, ShapeActivity::class.java).apply {
                        putExtra("color", color)
                    }
                    startActivity(intent)
                }
            }
            gridLayout.addView(button)
        }

        mainLayout.addView(gridLayout)
        setContentView(mainLayout)
    }
}
