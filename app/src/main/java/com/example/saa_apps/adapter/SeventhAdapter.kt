package com.example.saa_apps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.saa_apps.R

data class ListItem(val title: String, val subtitle: String, val imageRes: Int)

class SeventhAdapter(context: Context, items: List<ListItem>) :
    ArrayAdapter<ListItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_custom_list, parent, false)
        }

        val item = getItem(position)
        val imageView = itemView!!.findViewById<ImageView>(R.id.ivItemImage)
        val titleView = itemView.findViewById<TextView>(R.id.tvItemTitle)
        val subtitleView = itemView.findViewById<TextView>(R.id.tvItemSubTitle)

        item?.let {
            imageView.setImageResource(it.imageRes)
            titleView.text = it.title
            subtitleView.text = it.subtitle
        }

        return itemView
    }
}