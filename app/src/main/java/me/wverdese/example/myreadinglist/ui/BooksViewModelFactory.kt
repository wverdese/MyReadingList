package me.wverdese.example.myreadinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.wverdese.example.myreadinglist.data.BooksService
import javax.inject.Inject

class BooksViewModelFactory @Inject constructor(
    private val booksService: BooksService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = BooksViewModel(booksService) as T
}
