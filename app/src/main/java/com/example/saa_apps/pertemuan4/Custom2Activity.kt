package com.example.saa_apps.pertemuan4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.saa_apps.R

class Custom2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)

        Log.e("onCreate", "Custom2Activity dibuat pertama kali")

        val ivCustom2 = findViewById<ImageView>(R.id.ivCustom2)
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
        Log.e("onStart", "Custom2Activity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Custom2Activity dihapus dari stack")
    }
}