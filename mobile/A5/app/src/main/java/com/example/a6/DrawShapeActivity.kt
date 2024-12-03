package com.example.a6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DrawShapeActivity : AppCompatActivity() {
    private lateinit var customView: CustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Получаем данные из Intent
        val shapeType = intent.getStringExtra("shape") ?: "Circle"
        val sizeType = intent.getStringExtra("size") ?: "Medium"
        val color = intent.getIntExtra("color", Color.BLACK)

        // Создаем пользовательское представление для рисования фигуры
        customView = CustomView(this, shapeType, sizeType, color)

        setContentView(customView) // Устанавливаем созданное представление как содержимое активности
    }
}

// Пользовательское представление для рисования фигур
class CustomView(context: Context, private val shapeType: String, private val sizeType: String, private val newColor: Int) : View(context) {
    private val paint = Paint().apply {
        color = newColor;
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (shapeType) {
            "Circle" -> drawCircle(canvas)
            "Square" -> drawSquare(canvas)
            "Triangle" -> drawTriangle(canvas)
        }
    }

    private fun drawCircle(canvas: Canvas) {
        val radius = when (sizeType) {
            "Small" -> 50f
            "Medium" -> 100f
            else -> 150f // Large
        }
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }

    private fun drawSquare(canvas: Canvas) {
        val size = when (sizeType) {
            "Small" -> 100f
            "Medium" -> 200f
            else -> 300f // Large
        }
        canvas.drawRect((width - size) / 2f, (height - size) / 2f, (width + size) / 2f, (height + size) / 2f, paint)
    }

    private fun drawTriangle(canvas: Canvas) {
        val path = android.graphics.Path().apply {
            moveTo(width / 2f, height / 2f - 100f)
            lineTo(width / 2f - 100f, height / 2f + 100f)
            lineTo(width / 2f + 100f, height / 2f + 100f)
            close()
        }
        canvas.drawPath(path, paint)
    }
}
