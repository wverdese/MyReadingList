package me.wverdese.example.myreadinglist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.wverdese.example.myreadinglist.data.BooksService
import me.wverdese.example.myreadinglist.data.model.Book
import me.wverdese.example.myreadinglist.ui.BooksState.ShowToast.BookTapped
import me.wverdese.example.myreadinglist.ui.BooksState.ShowToast.NoItems
import me.wverdese.example.myreadinglist.ui.item.BookItemState
import me.wverdese.example.myreadinglist.util.NonNullMutableLiveData
import me.wverdese.example.myreadinglist.util.update
import javax.inject.Inject


class BooksViewModel @Inject constructor(private val booksService: BooksService) : ViewModel() {

    private val state = NonNullMutableLiveData(BooksState())

    fun state(): LiveData<BooksState> = state

    fun fetchData() {
        state.update { copy(isRefreshing = true) }

        viewModelScope.launch {
            kotlin.runCatching {
                booksService
                    .fetchBooks()
                    .sortedByDescending { it.publishDate }
                    .groupBy(
                        keySelector = { it.publishDate.year.toString() },
                        valueTransform = { modelToPresentation(it) }
                    )
            }
                .onSuccess { state.update { copy(bookItemStates = it, isRefreshing = false) } }
                .onFailure { t ->
                    Log.e(TAG, "error while fetching books", t) //log and propagate
                    state.update { copy(showToast = NoItems(), isRefreshing = false) }
                }
        }
    }

    private fun modelToPresentation(book: Book) =
        with(book) {
            BookItemState(
                titleText = title,
                subtitleText = author,
                coverImageUrl = coverImageUrl,
                onViewTapped = { onBookTapped(id) }
            )
        }

    private fun onBookTapped(bookId: String) {
        state.update { copy(showToast = BookTapped(bookId)) }
    }

    companion object {
        const val TAG = "BookViewModel"
    }
}
