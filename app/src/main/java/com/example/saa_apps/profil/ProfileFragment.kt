package com.example.saa_apps.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.saa_apps.R
import com.example.saa_apps.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar (PERBAIKAN: pakai binding.toolbar, bukan binding.root)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Profil Pengembang"
        }

        // Isi data profil (GANTI DENGAN DATA ANDA)
        binding.tvName.text = "Nama: NAFISA TAHERA"
        binding.tvNim.text = "NIM: 2457301106"
        binding.tvClass.text = "Kelas: 2SIA"
        binding.tvDescription.text = "Halo! Saya adalah pengembang aplikasi Bina Desa ini. " +
                "Aplikasi ini dibuat untuk memenuhi tugas praktikum dan membantu " +
                "pendataan serta pemantauan program pembangunan desa di wilayah binaan.\n\n" +
                "📧 Email: nafisa4si@mahasiswa.pcr.ac.id\n" +
                "📱 No. HP: 0987654"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}