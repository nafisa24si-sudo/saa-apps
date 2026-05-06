package com.example.saa_apps.Quiz   // ← UBAH PACKAGE

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.saa_apps.R
import com.example.saa_apps.databinding.ActivityVerifikasiBinding

class VerifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifikasiBinding
    private lateinit var sharedPref: SharedPreferences
    private var noHpDariRegistrasi: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        noHpDariRegistrasi = intent.getStringExtra("no_hp") ?: ""

        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        tvInfo.text = "Kode OTP telah dikirim ke nomor:\n$noHpDariRegistrasi"

        binding.btnVerifikasi.setOnClickListener {
            val kodeOtp = binding.etOtp.text.toString()

            if (kodeOtp.isNotEmpty() && kodeOtp == noHpDariRegistrasi) {
                val editor = sharedPref.edit()
                editor.putBoolean("isRegistered", true)
                editor.apply()

                AlertDialog.Builder(this)
                    .setTitle("Verifikasi Berhasil!")
                    .setMessage("Akun Anda berhasil didaftarkan. Silahkan login.")
                    .setPositiveButton("Login") { _, _ ->
                        val intent = Intent(this, LoginActivity::class.java)  // ← UBAH (pake LoginActivity di Quiz)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Verifikasi Gagal")
                    .setMessage("Kode OTP yang Anda masukkan salah!\n\nGunakan nomor handphone Anda sebagai kode OTP.")
                    .setPositiveButton("Coba Lagi") { dialog, _ ->
                        dialog.dismiss()
                        binding.etOtp.text?.clear()
                    }
                    .show()
            }
        }

        binding.btnBackToRegister.setOnClickListener {
            finish()
        }
    }
}