package com.example.saa_apps.Quiz   // ← UBAH PACKAGE

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.saa_apps.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnDaftar.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val noHp = binding.etNoHp.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (nama.isEmpty() || noHp.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPref.edit()
            editor.putString("reg_nama", nama)
            editor.putString("reg_no_hp", noHp)
            editor.putString("reg_username", username)
            editor.putString("reg_password", password)
            editor.apply()

            val intent = Intent(this, VerifikasiActivity::class.java)  // ← UBAH (sudah dalam package yang sama)
            intent.putExtra("no_hp", noHp)
            startActivity(intent)
        }

        binding.btnBackToLogin.setOnClickListener {
            finish()
        }
    }
}