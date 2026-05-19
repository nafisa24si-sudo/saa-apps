package com.example.saa_apps.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.saa_apps.R
import com.example.saa_apps.pertemuan4.AuthActivity
import com.example.saa_apps.pertemuan4.Custom1Activity
import com.example.saa_apps.pertemuan4.Custom2Activity
import com.example.saa_apps.pertemuan4.FourthActivity
import com.example.saa_apps.pertemuan4.RumusBangunRuangActivity
import com.example.saa_apps.pertemuan9.NinthActivity

class HomeFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val btnRumus = view.findViewById<Button>(R.id.btnRumusBangunRuang)
        val btnCustom1 = view.findViewById<Button>(R.id.btnCustom1)
        val btnCustom2 = view.findViewById<Button>(R.id.btnCustom2)
        val btnToFourth = view.findViewById<Button>(R.id.btnToFourth)
        val btnPertemuan9 = view.findViewById<Button>(R.id.btnPertemuan9)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        val username = sharedPref.getString("username", "User")
        tvUsername.text = "Halo, $username"

        btnRumus.setOnClickListener {
            startActivity(Intent(activity, RumusBangunRuangActivity::class.java))
        }
        btnCustom1.setOnClickListener {
            startActivity(Intent(activity, Custom1Activity::class.java))
        }
        btnCustom2.setOnClickListener {
            startActivity(Intent(activity, Custom2Activity::class.java))
        }
        btnToFourth.setOnClickListener {
            startActivity(Intent(activity, FourthActivity::class.java))
        }
        btnPertemuan9.setOnClickListener {
            startActivity(Intent(activity, NinthActivity::class.java))
        }
        btnLogout.setOnClickListener {
            sharedPref.edit().clear().apply()
            startActivity(Intent(activity, AuthActivity::class.java))
            requireActivity().finish()
        }
    }
}