package com.example.a8

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class MonitoringService : Service() {
    private val handler = Handler()
    private lateinit var checkRunnable: Runnable
    private var timer: Int = 0 // Таймер в секундах
    private lateinit var devices: List<String>

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        devices = intent.getStringArrayListExtra("devices") ?: listOf()
        timer = intent.getIntExtra("timer", 1)

        Toast.makeText(this, "Служба запущена. Устройства: $devices. Таймер: $timer сек.", Toast.LENGTH_SHORT).show()

        // Таймер для проверки
        checkRunnable = Runnable {
            checkDevicesStatus()
            handler.postDelayed(checkRunnable, (timer * 1000).toLong()) // Интервал в миллисекундах
        }
        handler.post(checkRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(checkRunnable)
        Toast.makeText(this, "Служба остановлена", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun checkDevicesStatus() {
        for (device in devices) {
            // Здесь добавьте реальную проверку состояния устройства
            Toast.makeText(this, "Проверка $device", Toast.LENGTH_SHORT).show()
        }
    }
}

