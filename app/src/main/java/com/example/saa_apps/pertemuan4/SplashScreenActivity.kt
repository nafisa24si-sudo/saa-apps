package com.example.saa_apps.pertemuan4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.saa_apps.R
import com.example.saa_apps.pertemuan11.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        lifecycleScope.launch {
            delay(2000)

            val onboardingFinished = sharedPref.getBoolean("onboarding_finished", false)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            val nextActivity = when {
                !onboardingFinished -> OnboardingActivity::class.java
                isLogin -> MainActivity::class.java
                else -> AuthActivity::class.java
            }

            startActivity(Intent(this@SplashScreenActivity, nextActivity))
            finish()
        }
    }
}