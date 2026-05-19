package com.example.saa_apps.pertemuan9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.saa_apps.R

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.listMessageItems)

        // DATA DENGAN NAMA BARU
        val messageList = listOf(
            MessageModel("Nafisa", "Halo! Ada yang bisa dibantu?", "https://randomuser.me/api/portraits/women/10.jpg"),
            MessageModel("Tahnia", "Besok kita rapat ya jam 10", "https://randomuser.me/api/portraits/women/20.jpg"),
            MessageModel("Santri", "Jangan lupa bawa tugas!", "https://randomuser.me/api/portraits/women/30.jpg"),
            MessageModel("Oza", "Makan siang bareng yuk!", "https://randomuser.me/api/portraits/men/40.jpg"),
            MessageModel("Nadira", "Udah pada ngumpul belum?", "https://randomuser.me/api/portraits/women/50.jpg"),
            MessageModel("Rizki", "Aku baru selesai kerja nih", "https://randomuser.me/api/portraits/men/60.jpg")
        )

        val adapter = MessageAdapter(requireContext(), messageList)
        listView.adapter = adapter
    }
}