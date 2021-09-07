package me.wverdese.example.myreadinglist.data.api

import com.google.gson.annotations.SerializedName


data class RemoteBook(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("cover_img_url")
    val coverImageUrl: String
)
