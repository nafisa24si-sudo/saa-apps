package com.example.saa_apps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saa_apps.R
import com.example.saa_apps.Pertemuan12.NoteFormActivity
import com.example.saa_apps.Pertemuan_2.SecondActivity
import com.example.saa_apps.Pertemuan_3.WelcomeActivity
import com.example.saa_apps.adapter.DashboardAdapter
import com.example.saa_apps.adapter.NewsAdapter
import com.example.saa_apps.databinding.FragmentHomeBinding
import com.example.saa_apps.model.DashboardMenuData
import com.example.saa_apps.model.NewsApiService
import com.example.saa_apps.model.NewsModel
import com.example.saa_apps.pertemuan11.EleventhActivity
import com.example.saa_apps.pertemuan13.ThirteenthActivity
import com.example.saa_apps.pertemuan4.AuthActivity
import com.example.saa_apps.pertemuan4.Custom1Activity
import com.example.saa_apps.pertemuan4.Custom2Activity
import com.example.saa_apps.pertemuan4.FourthActivity
import com.example.saa_apps.pertemuan4.Pertemuan1Activity
import com.example.saa_apps.pertemuan4.RumusBangunRuangActivity
import com.example.saa_apps.pertemuan5.WebViewActivity
import com.example.saa_apps.pertemuan9.NinthActivity
import com.example.saa_apps.pertemuan_10.TenthActivity
import com.example.saa_apps.utils.ReminderActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupHeader()
        setupRecyclerView()
        setupNewsRecyclerView()
        setupLogout()
        fetchNews()
    }

    private fun setupHeader() {
        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val name = sharedPref.getString("username", "Pengguna")
        binding.tvGreeting.text = getString(R.string.greeting_user, name)
    }

    private fun setupRecyclerView() {
        val menuAdapter = DashboardAdapter(DashboardMenuData.items) { menu ->
            val intent = when (menu.route) {
                "p1" -> Intent(requireContext(), Pertemuan1Activity::class.java)
                "p2" -> Intent(requireContext(), SecondActivity::class.java)
                "p3" -> Intent(requireContext(), WelcomeActivity::class.java)
                "p4" -> Intent(requireContext(), FourthActivity::class.java)
                "p5" -> Intent(requireContext(), WebViewActivity::class.java)
                "p6" -> Intent(requireContext(), RumusBangunRuangActivity::class.java)
                "p7" -> Intent(requireContext(), Custom1Activity::class.java)
                "p8" -> Intent(requireContext(), Custom2Activity::class.java)
                "p9" -> Intent(requireContext(), NinthActivity::class.java)
                "p10" -> Intent(requireContext(), TenthActivity::class.java)
                "p11" -> Intent(requireContext(), EleventhActivity::class.java)
                "p12" -> Intent(requireContext(), NoteFormActivity::class.java)
                "p13" -> Intent(requireContext(), ThirteenthActivity::class.java)
                "p14" -> Intent(requireContext(), ReminderActivity::class.java)
                else -> null
            }

            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "${menu.title} belum tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvDashboardMenu.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun setupNewsRecyclerView() {
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NewsApiService::class.java)
        apiService.getNews().enqueue(object : Callback<List<NewsModel>> {
            override fun onResponse(call: Call<List<NewsModel>>, response: Response<List<NewsModel>>) {
                if (isAdded && response.isSuccessful) {
                    val newsList = response.body()?.take(10) ?: emptyList()
                    newsAdapter = NewsAdapter(newsList)
                    binding.rvNews.adapter = newsAdapter
                }
            }

            override fun onFailure(call: Call<List<NewsModel>>, t: Throwable) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "Gagal memuat berita", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            
            startActivity(Intent(requireContext(), AuthActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}