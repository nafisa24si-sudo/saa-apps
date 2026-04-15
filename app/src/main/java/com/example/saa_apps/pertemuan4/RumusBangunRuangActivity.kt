package com.example.saa_apps.pertemuan4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.saa_apps.R

class RumusBangunRuangActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumus_bangun_ruang)

        Log.e("onCreate", "RumusBangunRuangActivity dibuat pertama kali")

        val tvJudul = findViewById<TextView>(R.id.tvJudul)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsi)
        val btnBack = findViewById<Button>(R.id.btnBack)

        tvJudul.text = intent.getStringExtra("JUDUL")
        tvDeskripsi.text = intent.getStringExtra("DESKRIPSI")

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "RumusBangunRuangActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "RumusBangunRuangActivity dihapus dari stack")
    }
}