package com.example.saa_apps.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.saa_apps.R
import com.example.saa_apps.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Tentang Bina Desa"
        }

        // Isi konten Bina Desa
        binding.tvPurpose.text = "📖 Bina Desa"
        binding.tvDefinition.text = "Bina Desa adalah program pemberdayaan masyarakat desa yang bertujuan untuk meningkatkan kesejahteraan melalui pendampingan dan pengembangan potensi lokal secara berkelanjutan."

        binding.tvFeatures.text = "✨ Fitur Aplikasi Bina Desa:\n\n" +
                "• 📊 Dashboard Informasi Desa\n" +
                "• 📝 Manajemen Program Kerja\n" +
                "• 📈 Laporan Kegiatan Bulanan\n" +
                "• 🖼️ Galeri Dokumentasi\n" +
                "• 💬 Forum Diskusi Warga\n" +
                "• 📍 Peta Potensi Desa"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}