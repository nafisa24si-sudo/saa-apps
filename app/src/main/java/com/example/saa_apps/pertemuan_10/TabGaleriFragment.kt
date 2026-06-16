package com.example.saa_apps.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.saa_apps.databinding.FragmentTabGaleriBinding

class TabGaleriFragment : Fragment() {

    private var _binding: FragmentTabGaleriBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabGaleriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listGallery = listOf(
            GalleryModel("Kantor Desa", "https://picsum.photos/id/101/400/300"),
            GalleryModel("Kegiatan Gotong Royong", "https://picsum.photos/id/111/400/300"),
            GalleryModel("Posyandu Melati", "https://picsum.photos/id/202/400/300"),
            GalleryModel("UMKM Kerajinan Bambu", "https://picsum.photos/id/303/400/300"),
            GalleryModel("Wisata Sawah Hijau", "https://picsum.photos/id/404/400/300"),
            GalleryModel("Pembangunan Jembatan", "https://picsum.photos/id/505/400/300"),
            GalleryModel("Rapat Desa", "https://picsum.photos/id/606/400/300"),
            GalleryModel("Panen Raya", "https://picsum.photos/id/707/400/300"),
            GalleryModel("Pelatihan Digital", "https://picsum.photos/id/808/400/300"),
            GalleryModel("Pasar Tradisional", "https://picsum.photos/id/909/400/300")
        )

        val adapter = GalleryAdapter(listGallery)
        binding.rvGallery.layoutManager = GridLayoutManager(context, 2)
        binding.rvGallery.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}