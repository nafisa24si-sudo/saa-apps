package com.example.saa_apps.pertemuan9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.saa_apps.R
import com.example.saa_apps.data.AppDatabase
import com.example.saa_apps.data.entity.MessageEntity
import kotlinx.coroutines.launch

class MessageFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<MessageEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        val listView = view.findViewById<ListView>(R.id.listMessageItems)
        adapter = MessageAdapter(requireContext(), messages)
        listView.adapter = adapter

        fetchMessages()

        // Seeder: tambah data awal jika kosong
        lifecycleScope.launch {
            val count = db.messageDao().getAll().size
            if (count == 0) {
                seedMessages()
            }
        }
    }

    private fun fetchMessages() {
        lifecycleScope.launch {
            val data = db.messageDao().getAll()
            messages.clear()
            messages.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    private suspend fun seedMessages() {
        val seedData = listOf(
            MessageEntity(
                senderName = "Nafisa",
                messageText = "Halo! Ada yang bisa dibantu?",
                avatarUrl = "https://randomuser.me/api/portraits/women/10.jpg"
            ),
            MessageEntity(
                senderName = "Tahnia",
                messageText = "Besok kita rapat ya jam 10",
                avatarUrl = "https://randomuser.me/api/portraits/women/20.jpg"
            ),
            MessageEntity(
                senderName = "Santri",
                messageText = "Jangan lupa bawa tugas!",
                avatarUrl = "https://randomuser.me/api/portraits/women/30.jpg"
            ),
            MessageEntity(
                senderName = "Oza",
                messageText = "Makan siang bareng yuk!",
                avatarUrl = "https://randomuser.me/api/portraits/men/40.jpg"
            ),
            MessageEntity(
                senderName = "Nadira",
                messageText = "Udah pada ngumpul belum?",
                avatarUrl = "https://randomuser.me/api/portraits/women/50.jpg"
            ),
            MessageEntity(
                senderName = "Rizki",
                messageText = "Aku baru selesai kerja nih",
                avatarUrl = "https://randomuser.me/api/portraits/men/60.jpg"
            )
        )
        seedData.forEach { db.messageDao().insert(it) }
        fetchMessages()
    }

    override fun onResume() {
        super.onResume()
        fetchMessages()
    }
}