package com.example.saa_apps.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R
import com.example.saa_apps.databinding.ActivityReminderBinding

class ReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Atur Pengingat"

        val kegiatanTitle = intent.getStringExtra("kegiatan_title") ?: "Kegiatan Desa"
        val kegiatanDesc = intent.getStringExtra("kegiatan_desc") ?: "Jangan lupa ikut kegiatan!"

        binding.tvKegiatan.text = "Kegiatan: $kegiatanTitle"

        // CEK IZIN ALARM (KHUSUS ANDROID 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                // Minta izin ke pengaturan
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
                Toast.makeText(this, "Izinkan pengingat untuk mengatur alarm!", Toast.LENGTH_LONG).show()
            }
        }

        binding.etMinutes.setText("1")

        binding.btnSetReminder.setOnClickListener {
            val minutes = binding.etMinutes.text.toString()

            if (minutes.isEmpty()) {
                Toast.makeText(this, "Masukkan durasi menit!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val minutesInt = minutes.toIntOrNull()

            if (minutesInt == null || minutesInt <= 0) {
                Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // CEK LAGI IZIN SEBELUM SET ALARM
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                if (!alarmManager.canScheduleExactAlarms()) {
                    Toast.makeText(this, "Mohon izinkan pengingat di pengaturan!", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    startActivity(intent)
                    return@setOnClickListener
                }
            }

            setReminder(
                title = "Pengingat: $kegiatanTitle",
                message = "$kegiatanDesc - dalam $minutesInt menit",
                minutesFromNow = minutesInt
            )

            Toast.makeText(
                this,
                "⏰ Pengingat akan muncul dalam $minutesInt menit!",
                Toast.LENGTH_LONG
            ).show()

            finish()
        }
    }

    private fun setReminder(
        title: String,
        message: String,
        minutesFromNow: Int
    ) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, NotificationReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = System.currentTimeMillis() + (minutesFromNow * 60 * 1000)

        // ✅ PERBAIKAN: Pakai metode yang kompatibel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}