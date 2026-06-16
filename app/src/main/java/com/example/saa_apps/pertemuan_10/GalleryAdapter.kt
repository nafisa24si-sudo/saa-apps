package com.example.saa_apps.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saa_apps.databinding.ItemGalleryBinding

class GalleryAdapter(private val listGallery: List<GalleryModel>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gallery: GalleryModel) {
            with(binding) {
                tvTitle.text = gallery.title
                Glide.with(itemView.context)
                    .load(gallery.imageUrl)
                    .into(ivGallery)
                
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, gallery.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(listGallery[position])
    }

    override fun getItemCount(): Int = listGallery.size
}