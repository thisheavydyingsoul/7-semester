package com.example.a7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a7.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Убедитесь, что у вас есть соответствующий макет
        finish();
    }
}