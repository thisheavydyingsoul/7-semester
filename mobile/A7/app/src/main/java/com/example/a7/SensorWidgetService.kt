package com.example.a7

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class SensorRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return SensorRemoteViewsFactory(applicationContext)
    }
}

class SensorRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory, SensorEventListener {

    private val sensors = mutableListOf<Sensor>()
    private val sensorValues = mutableMapOf<Int, Float>()
    private lateinit var sensorManager: SensorManager

    companion object {
        private var sensorStates = mutableMapOf<Int, Boolean>()
        private lateinit var sensors: List<Sensor> // Declare sensors here
        private val sensorValues = mutableMapOf<Int, Float>()

        fun toggleDisplayState(position: Int) {
            val currentState = sensorStates[position] ?: false
            sensorStates[position] = !currentState
        }

        fun getSensorCount(): Int {
            return sensors.size // Access sensors from here
        }

        fun getSensorValue(position: Int): Float? {
            return sensorValues[position]
        }

        fun getSensorName(position: Int): String {
            return sensors[position].name
        }

        fun isDisplayingValue(position: Int): Boolean {
            return sensorStates[position] ?: false
        }
    }

    override fun onCreate() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensors.addAll(sensorManager.getSensorList(Sensor.TYPE_ALL))

        sensors.forEachIndexed { index, _ ->
            sensorStates[index] = false // Показываем название по умолчанию
            sensorValues[index] = 0.0f  // Начальное значение
        }

        // Регистрируем слушатель для всех сенсоров
        sensors.forEach { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onDataSetChanged() {
        Log.d("SensorRemoteViewsFactory", "onDataSetChanged called")
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        sensors.clear()
        sensorStates.clear()
        sensorValues.clear()
    }

    override fun getCount(): Int {
        return sensors.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, android.R.layout.simple_list_item_1)

        val isDisplayingValue = sensorStates[position] ?: false

        if (isDisplayingValue) {
            // Показываем значение датчика
            val value = sensorValues[position]?.toString() ?: "N/A"
            views.setTextViewText(android.R.id.text1, "${sensors[position].name}: $value")
        } else {
            // Показываем название датчика
            views.setTextViewText(android.R.id.text1, sensors[position].name)
        }

        Log.d("SensorRemoteViewsFactory", "getViewAt called for position $position")

        // Настраиваем FillInIntent для передачи позиции
        val intent = Intent().apply {
            putExtra("POSITION", position)
            action = "com.example.sensorwidget.ACTION_CLICK"
        }
        views.setOnClickFillInIntent(android.R.id.text1, intent)

        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val sensorIndex = sensors.indexOf(event.sensor)
            if (sensorIndex != -1) {
                // Обновляем значение датчика
                sensorValues[sensorIndex] = event.values[0] // Первое значение датчика
                Log.d("SensorRemoteViewsFactory", "Sensor value updated for position $sensorIndex: ${event.values[0]}")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Этот метод не используется в данной реализации.
    }
}