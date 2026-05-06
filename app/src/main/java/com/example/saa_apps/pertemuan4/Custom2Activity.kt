package com.example.saa_apps.pertemuan4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.saa_apps.R
import com.google.android.material.appbar.MaterialToolbar

class Custom2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)

        Log.e("onCreate", "Custom2Activity dibuat pertama kali")

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvJudul = findViewById<TextView>(R.id.tvJudul)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsi)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnWebView = findViewById<Button>(R.id.btnWebView)

        tvJudul.text = intent.getStringExtra("JUDUL")
        tvDeskripsi.text = intent.getStringExtra("DESKRIPSI")

        btnBack.setOnClickListener {
            finish()
        }

        btnWebView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://nafisa24si.alwaysdata.net/dashboard")
            )
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
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