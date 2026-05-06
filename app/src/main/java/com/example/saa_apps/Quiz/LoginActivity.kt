package com.example.saa_apps.Quiz   // ← PACKAGE QUIZ

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R
import com.example.saa_apps.pertemuan4.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            loginUser()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val regUsername = sharedPref.getString("reg_username", "")
        val regPassword = sharedPref.getString("reg_password", "")

        val isValid = (username == password) ||
                (username == regUsername && password == regPassword)

        if (isValid) {
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", true)
            editor.putString("username", username)
            editor.apply()

            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Login Gagal")
                .setMessage("Username atau Password salah!\n\nPetunjuk:\n1. Username = Password\n2. Atau gunakan akun yang sudah terdaftar")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}