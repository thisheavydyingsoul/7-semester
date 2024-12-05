package com.example.a8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wifiCheckBox = findViewById<CheckBox>(R.id.checkbox_wifi)
        val bluetoothCheckBox = findViewById<CheckBox>(R.id.checkbox_bluetooth)
        val gpsCheckBox = findViewById<CheckBox>(R.id.checkbox_gps)
        val timerInput = findViewById<EditText>(R.id.timer_input)

        val startServiceButton = findViewById<Button>(R.id.start_service_button)
        val stopServiceButton = findViewById<Button>(R.id.stop_service_button)

        startServiceButton.setOnClickListener {
            val selectedDevices = mutableListOf<String>()
            if (wifiCheckBox.isChecked) selectedDevices.add("Wi-Fi")
            if (bluetoothCheckBox.isChecked) selectedDevices.add("Bluetooth")
            if (gpsCheckBox.isChecked) selectedDevices.add("GPS")

            val timer = timerInput.text.toString().toIntOrNull() ?: 0 // Таймер в секундах

            val intent = Intent(this, MonitoringService::class.java)
            intent.putStringArrayListExtra("devices", ArrayList(selectedDevices))
            intent.putExtra("timer", timer) // Передаем таймер
            startService(intent)
        }


        stopServiceButton.setOnClickListener {
            stopService(Intent(this, MonitoringService::class.java))
        }
    }
}
