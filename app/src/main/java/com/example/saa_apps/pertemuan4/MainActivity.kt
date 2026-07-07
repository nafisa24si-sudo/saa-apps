package com.example.saa_apps.pertemuan4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.saa_apps.R
import com.example.saa_apps.about.AboutFragment
import com.example.saa_apps.databinding.ActivityMainBinding
import com.example.saa_apps.home.HomeFragment
import com.example.saa_apps.pertemuan9.MessageFragment
import com.example.saa_apps.profil.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set fragment awal (Home) saat aplikasi dibuka
        loadFragment(HomeFragment())

        // Setup Listener untuk Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_about -> {
                    loadFragment(AboutFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.nav_messages -> {
                    loadFragment(MessageFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}