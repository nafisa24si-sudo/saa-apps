package com.example.saa_apps.pertemuan11

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("url")
    val url: String
)

interface ApiService {
    @retrofit2.http.GET("photos")
    fun getPhotos(): retrofit2.Call<List<PhotoModel>>
}