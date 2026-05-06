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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var tvData: TextView
    private lateinit var btnShowSnackbar: Button
    private lateinit var btnShowAlertDialog: Button
    private lateinit var btnWebView: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvData = findViewById(R.id.tvData)
        btnShowSnackbar = findViewById(R.id.btnShowSnackbar)
        btnShowAlertDialog = findViewById(R.id.btnShowAlertDialog)
        btnWebView = findViewById(R.id.btnWebView)
        btnBack = findViewById(R.id.btnBack)

        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)

        tvData.text = """
            Nama : $name
            Asal : $from
            Usia : $age Tahun
        """.trimIndent()

        btnShowSnackbar.setOnClickListener {
            Snackbar.make(it, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") { }
                .show()
        }

        btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(it, "Anda memilih Ya", Snackbar.LENGTH_SHORT).show()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(it, "Anda memilih Batal", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

        btnWebView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://nafisa24si.alwaysdata.net/dashboard")
            )
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
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