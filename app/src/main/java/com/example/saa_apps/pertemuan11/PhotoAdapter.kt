package com.example.saa_apps.pertemuan11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saa_apps.databinding.ItemPhotoBinding

class PhotoAdapter(private val photos: List<PhotoModel>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoModel) {
            binding.tvTitle.text = photo.title
            binding.tvUrl.text = photo.url
            Glide.with(binding.ivPhoto.context)
                .load(photo.thumbnailUrl)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}