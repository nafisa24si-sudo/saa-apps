package com.example.saa_apps.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.saa_apps.R
import com.example.saa_apps.databinding.FragmentHomeBinding
import com.example.saa_apps.Pertemuan_2.SecondActivity
import com.example.saa_apps.Quiz.LoginActivity
import com.example.saa_apps.pertemuan4.Custom1Activity
import com.example.saa_apps.pertemuan4.Custom2Activity
import com.example.saa_apps.pertemuan4.FourthActivity
import com.example.saa_apps.pertemuan4.RumusBangunRuangActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // Tombol Rumus Bangun Ruang
        binding.btnRumusBangunRuang.setOnClickListener {
            startActivity(Intent(requireContext(), RumusBangunRuangActivity::class.java))
        }

        // Tombol Custom 1
        binding.btnCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom1Activity::class.java))
        }

        // Tombol Custom 2
        binding.btnCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), Custom2Activity::class.java))
        }

        // Tombol Fourth Activity
        binding.btnFourthActivity.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}