package me.wverdese.example.myreadinglist.data.model

import org.threeten.bp.LocalDate

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val publishDate: LocalDate,
    val coverImageUrl: String
)
