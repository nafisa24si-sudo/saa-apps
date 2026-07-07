package com.example.saa_apps.pertemuan11

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saa_apps.databinding.ActivityEleventhBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EleventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleventhBinding
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pertemuan 11: Retrofit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        fetchPhotos()
    }

    private fun setupRecyclerView() {
        binding.rvPhotos.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchPhotos() {
        binding.progressBar.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val photos = response.body() ?: emptyList()
                    // Ambil 50 foto saja agar tidak terlalu berat
                    adapter = PhotoAdapter(photos.take(50))
                    binding.rvPhotos.adapter = adapter
                } else {
                    Toast.makeText(this@EleventhActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@EleventhActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}