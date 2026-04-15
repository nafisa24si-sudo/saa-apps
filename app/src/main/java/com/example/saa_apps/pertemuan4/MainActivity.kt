package com.example.saa_apps.pertemuan4

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.saa_apps.Pertemuan_3.LoginActivity
import com.example.saa_apps.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var btnRumus: Button
    private lateinit var btnCustom1: Button
    private lateinit var btnCustom2: Button
    private lateinit var btnToFourth: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Log LifeCycle
        Log.e("onCreate", "MainActivity dibuat pertama kali")

        tvUsername = findViewById(R.id.tvUsername)
        btnRumus = findViewById(R.id.btnRumusBangunRuang)
        btnCustom1 = findViewById(R.id.btnCustom1)
        btnCustom2 = findViewById(R.id.btnCustom2)
        btnToFourth = findViewById(R.id.btnToFourth)
        btnLogout = findViewById(R.id.btnLogout)

        // Ambil username dari Intent
        val username = intent.getStringExtra("USERNAME") ?: "User"
        tvUsername.text = "Halo, $username"

        // Tombol ke RumusBangunRuang
        btnRumus.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            intent.putExtra("JUDUL", "Rumus Bangun Ruang")
            intent.putExtra("DESKRIPSI", "Hitung volume dan luas permukaan bangun ruang")
            startActivity(intent)
        }

        // Tombol ke Custom1
        btnCustom1.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("JUDUL", "Custom 1")
            intent.putExtra("DESKRIPSI", "Ini adalah halaman custom pertama dengan gambar")
            startActivity(intent)
        }

        // Tombol ke Custom2
        btnCustom2.setOnClickListener {
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("JUDUL", "Custom 2")
            intent.putExtra("DESKRIPSI", "Ini adalah halaman custom kedua dengan gambar")
            startActivity(intent)
        }

        // Tombol ke FourthActivity (kirim data)
        btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Tombol Logout dengan konfirmasi
        btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "MainActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "MainActivity dihapus dari stack")
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(findViewById(android.R.id.content), "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }
}