package me.wverdese.example.myreadinglist.ui.item


data class BookItemState(
    val titleText: String,
    val subtitleText: String,
    val coverImageUrl: String,
    val onViewTapped: (() -> Unit)? = null
)
