package com.example.saa_apps.pertemuan4

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.saa_apps.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var btnRumus: Button
    private lateinit var btnCustom1: Button
    private lateinit var btnCustom2: Button
    private lateinit var btnToFourth: Button
    private lateinit var btnOpenWebsite: Button
    private lateinit var btnLogout: Button
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("onCreate", "MainActivity dibuat pertama kali")

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        tvUsername = findViewById(R.id.tvUsername)
        btnRumus = findViewById(R.id.btnRumusBangunRuang)
        btnCustom1 = findViewById(R.id.btnCustom1)
        btnCustom2 = findViewById(R.id.btnCustom2)
        btnToFourth = findViewById(R.id.btnToFourth)
        btnOpenWebsite = findViewById(R.id.btnOpenWebsite)
        btnLogout = findViewById(R.id.btnLogout)

        val username = sharedPref.getString("username", "User")
        tvUsername.text = "Halo, $username"

        btnRumus.setOnClickListener {
            startActivity(Intent(this, RumusBangunRuangActivity::class.java).apply {
                putExtra("JUDUL", "Rumus Bangun Ruang")
                putExtra("DESKRIPSI", "Hitung volume dan luas permukaan bangun ruang")
            })
        }

        btnCustom1.setOnClickListener {
            startActivity(Intent(this, Custom1Activity::class.java).apply {
                putExtra("JUDUL", "Custom 1")
                putExtra("DESKRIPSI", "Ini adalah halaman custom pertama")
            })
        }

        btnCustom2.setOnClickListener {
            startActivity(Intent(this, Custom2Activity::class.java).apply {
                putExtra("JUDUL", "Custom 2")
                putExtra("DESKRIPSI", "Ini adalah halaman custom kedua")
            })
        }

        btnToFourth.setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java).apply {
                putExtra("name", "Politeknik Caltex Riau")
                putExtra("from", "Rumbai")
                putExtra("age", 25)
            })
        }

        btnOpenWebsite.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://nafisa24si.alwaysdata.net/dashboard")
            )
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                sharedPref.edit().clear().apply()

                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Logout dibatalkan",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .show()
    }
}