package com.example.saa_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saa_apps.databinding.ItemNewsBinding
import com.example.saa_apps.model.NewsModel

class NewsAdapter(private val newsList: List<NewsModel>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsModel) {
            binding.tvNewsTitle.text = news.title
            binding.tvNewsDesc.text = news.body
            binding.tvNewsDate.text = "Berita Terbaru"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
}