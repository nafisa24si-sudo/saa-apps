package com.example.saa_apps.pertemuan4

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saa_apps.R
import com.example.saa_apps.adapter.ListItem
import com.example.saa_apps.adapter.SeventhAdapter

class SeventhActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pertemuan 7: ListView"

        val listView = findViewById<ListView>(R.id.lvMain)

        val items = listOf(
            ListItem("Item 1", "Deskripsi item pertama", R.drawable.ic_logo),
            ListItem("Item 2", "Deskripsi item kedua", R.drawable.ic_logo),
            ListItem("Item 3", "Deskripsi item ketiga", R.drawable.ic_logo),
            ListItem("Item 4", "Deskripsi item keempat", R.drawable.ic_logo),
            ListItem("Item 5", "Deskripsi item kelima", R.drawable.ic_logo)
        )

        val adapter = SeventhAdapter(this, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(this, "Klik: ${selectedItem.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}