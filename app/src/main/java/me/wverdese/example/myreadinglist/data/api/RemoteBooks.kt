package me.wverdese.example.myreadinglist.data.api

import com.google.gson.annotations.SerializedName


data class RemoteBooks(
    @SerializedName("books")
    val books: List<RemoteBook>
)
