package com.example.saa_apps.pertemuan11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.saa_apps.databinding.ActivityOnboardingBinding
import com.example.saa_apps.pertemuan4.AuthActivity
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onboardingItems = listOf(
            OnboardingItem(
                "Selamat Datang",
                "Aplikasi Bina Desa membantu Anda mengelola informasi desa dengan lebih mudah dan efisien."
            ),
            OnboardingItem(
                "Pantau Pembangunan",
                "Dapatkan informasi terkini mengenai proyek pembangunan dan layanan publik di desa Anda."
            ),
            OnboardingItem(
                "Layanan Terintegrasi",
                "Nikmati kemudahan akses layanan administrasi desa langsung dari genggaman Anda."
            )
        )

        val adapter = OnboardingAdapter(onboardingItems)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onboardingItems.size - 1) {
                    binding.btnNext.visibility = View.GONE
                    binding.btnStart.visibility = View.VISIBLE
                } else {
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.GONE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            binding.viewPager.currentItem += 1
        }

        binding.btnStart.setOnClickListener {
            markOnboardingFinished()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }

    private fun markOnboardingFinished() {
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("onboarding_finished", true).apply()
    }
}

data class OnboardingItem(val title: String, val description: String)
