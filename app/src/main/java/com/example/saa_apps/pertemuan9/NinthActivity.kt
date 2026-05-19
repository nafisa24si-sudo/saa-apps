package com.example.saa_apps.pertemuan9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R
import com.example.saa_apps.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ChipGroup Listener
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Snackbar.make(binding.root, "Filter: ${chip.text}", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Login Button dengan validasi Email & Password
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.textInputLayoutEmail.error = "Email tidak boleh kosong"
            } else if (password.isEmpty()) {
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = "Password tidak boleh kosong"
            } else {
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = null
                Toast.makeText(this, "Login dengan:\nEmail: $email\nPassword: $password", Toast.LENGTH_LONG).show()
            }
        }

        // Grid Menu Buttons
        binding.btnMenu1.setOnClickListener { Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show() }
        binding.btnMenu2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MessageFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.btnMenu3.setOnClickListener { Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show() }
        binding.btnMenu4.setOnClickListener { Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show() }
        binding.btnMenu5.setOnClickListener { Toast.makeText(this, "Menu 5", Toast.LENGTH_SHORT).show() }
        binding.btnMenu6.setOnClickListener { Toast.makeText(this, "Menu 6", Toast.LENGTH_SHORT).show() }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}