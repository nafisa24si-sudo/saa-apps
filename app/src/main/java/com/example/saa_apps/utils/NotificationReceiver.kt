package com.example.saa_apps.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.saa_apps.pertemuan4.MainActivity

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat Bina Desa"
        val message = intent.getStringExtra("message") ?: "Waktunya kegiatan desa!"

        // Intent untuk membuka MainActivity saat notifikasi diklik
        val targetIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notificationHelper = NotificationHelper
        notificationHelper.showNotification(context, title, message, targetIntent)
    }
}