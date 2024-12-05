package com.example.a7

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

class SensorWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "com.example.sensorwidget.ACTION_CLICK") {
            if (intent.hasExtra("POSITION")) {
                val position = intent.getIntExtra("POSITION", -1)
                Log.d("SensorWidget", "Clicked position: $position")

                if (position != -1) {
                    // Меняем состояние отображения
                    SensorRemoteViewsFactory.toggleDisplayState(position)

                    // Обновляем данные в RemoteViewsFactory
                    val appWidgetManager = AppWidgetManager.getInstance(context)
                    val appWidgetIds = appWidgetManager.getAppWidgetIds(
                        ComponentName(context, SensorWidget::class.java)
                    )

                    // Уведомляем виджет о том, что данные изменились
                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId)
                    }
                }
            }
        }
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            // Обновляем данные вручную
            val sensorCount = SensorRemoteViewsFactory.getSensorCount()
            for (i in 0 until sensorCount) {
                val sensorValue = SensorRemoteViewsFactory.getSensorValue(i)
                val sensorName = SensorRemoteViewsFactory.getSensorName(i)
                val isDisplayingValue = SensorRemoteViewsFactory.isDisplayingValue(i)

                // Настраиваем отображение
                views.setTextViewText(R.id.sensor_list_view_item, if (isDisplayingValue) "$sensorName: $sensorValue" else sensorName)

                // Настраиваем FillInIntent для передачи позиции
                val intent = Intent().apply {
                    putExtra("POSITION", i)
                    action = "com.example.sensorwidget.ACTION_CLICK"
                }
                views.setOnClickFillInIntent(R.id.sensor_list_view_item, intent)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}