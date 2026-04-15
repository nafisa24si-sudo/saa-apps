package com.example.saa_apps.pertemuan4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.saa_apps.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        val tvData = findViewById<TextView>(R.id.tvData)
        val btnShowSnackbar = findViewById<Button>(R.id.btnShowSnackbar)
        val btnShowAlertDialog = findViewById<Button>(R.id.btnShowAlertDialog)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnCard = findViewById<Button>(R.id.btnCard)
        val tvCardTitle = findViewById<TextView>(R.id.tvCardTitle)

        // Ambil data dari Intent
        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)

        // Tampilkan data di TextView
        tvData.text = "Nama: $name\nAsal: $from\nUsia: $age"

        // Tampilkan di Logcat
        Log.e("Data Intent", "Nama: $name, Usia: $age, Asal: $from")

        // Set judul card
        tvCardTitle.text = name

        // Snackbar
        btnShowSnackbar.setOnClickListener {
            Snackbar.make(it, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        // AlertDialog
        btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                    Snackbar.make(it, "Anda memilih Ya", Snackbar.LENGTH_SHORT).show()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                    Snackbar.make(it, "Anda memilih Batal", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

        // Tombol Card
        btnCard.setOnClickListener {
            Snackbar.make(it, "Tombol di dalam Card diklik!", Snackbar.LENGTH_SHORT).show()
        }

        // Tombol Kembali ke MainActivity
        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }
}