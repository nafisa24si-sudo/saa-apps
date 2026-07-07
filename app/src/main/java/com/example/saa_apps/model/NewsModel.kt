package com.example.saa_apps.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

interface NewsApiService {
    @retrofit2.http.GET("posts")
    fun getNews(): retrofit2.Call<List<NewsModel>>
}