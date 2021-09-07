package me.wverdese.example.myreadinglist.ui

import me.wverdese.example.myreadinglist.ui.item.BookItemState
import me.wverdese.example.myreadinglist.util.SimpleStateEvent

typealias BooksItemStatesByYear = Map<String, List<BookItemState>>

data class BooksState(
    val isRefreshing: Boolean = false,
    val bookItemStates: BooksItemStatesByYear = emptyMap(),
    val showToast: ShowToast? = null
) {

    sealed class ShowToast : SimpleStateEvent() {
        class NoItems: ShowToast()
        class BookTapped(val bookId: String) : ShowToast()
    }
}
