package com.example.saa_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saa_apps.databinding.ItemDashboardMenuBinding
import com.example.saa_apps.model.DashboardMenu

class DashboardAdapter(
    private val items: List<DashboardMenu>,
    private val onItemClick: (DashboardMenu) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDashboardMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemDashboardMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardMenu) {
            binding.ivMenuIcon.setImageResource(item.iconRes)
            binding.tvMenuLabel.text = item.title
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}