package com.example.saa_apps.pertemuan4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.saa_apps.Pertemuan12.NoteFragment
import com.example.saa_apps.R
import com.example.saa_apps.about.AboutFragment
import com.example.saa_apps.home.HomeFragment
import com.example.saa_apps.pertemuan9.MessageFragment
import com.example.saa_apps.profil.ProfileFragment
import com.example.saa_apps.utils.ReminderActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Bina Desa"

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.nav_message -> {
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.nav_note -> {
                    replaceFragment(NoteFragment())
                    true
                }
                R.id.nav_reminder -> {
                    // ReminderActivity adalah Activity, jadi kita start activity saja
                    startActivity(Intent(this, ReminderActivity::class.java))
                    false // return false agar icon bottom nav tidak berpindah karena ini bukan fragment
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}