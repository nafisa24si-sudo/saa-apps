package com.example.saa_apps.pertemuan4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R

class AuthActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        // contoh login sederhana
        if (username == "admin" && password == "12345") {

            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", true)
            editor.putString("username", username)
            editor.apply()

            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
        }
    }
}