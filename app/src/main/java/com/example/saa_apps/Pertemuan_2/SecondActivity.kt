package com.example.saa_apps.Pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pertemuan 2"

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val text = etInput.text.toString()
            if (text.isNotEmpty()) {
                Toast.makeText(this, "Input: $text", Toast.LENGTH_SHORT).show()
                Log.d("Pertemuan2", "User input: $text")
            } else {
                Toast.makeText(this, "Silakan isi input dulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}