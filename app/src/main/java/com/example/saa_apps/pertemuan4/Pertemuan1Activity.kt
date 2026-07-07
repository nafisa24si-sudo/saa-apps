package com.example.saa_apps.pertemuan4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R

class Pertemuan1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pertemuan1)
        
        // Mengaktifkan tombol kembali
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Bina Desa"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}