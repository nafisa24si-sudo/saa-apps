package com.example.saa_apps.pertemuan9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.saa_apps.R
import com.example.saa_apps.data.entity.MessageEntity

class MessageAdapter(
    context: Context,
    private val messages: List<MessageEntity>  // UBAH KE MessageEntity
) : ArrayAdapter<MessageEntity>(context, 0, messages) {  // UBAH KE MessageEntity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        }

        val message = messages[position]

        val avatarImg = view!!.findViewById<ImageView>(R.id.avatarImg)
        val textSender = view.findViewById<TextView>(R.id.textSender)
        val textMessage = view.findViewById<TextView>(R.id.textMessage)

        Glide.with(context)
            .load(message.avatarUrl)
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .circleCrop()
            .into(avatarImg)

        textSender.text = message.senderName
        textMessage.text = message.messageText

        view.setOnClickListener {
            android.widget.Toast.makeText(
                context,
                "Pesan dari ${message.senderName}: ${message.messageText}",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }
}