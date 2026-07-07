package com.example.saa_apps.Pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tambahkan Toolbar Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Bina Desa"

        val username = intent.getStringExtra("USERNAME") ?: "Pengguna"
        binding.tvUserName.text = username
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}