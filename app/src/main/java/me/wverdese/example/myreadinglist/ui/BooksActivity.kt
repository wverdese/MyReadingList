package me.wverdese.example.myreadinglist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_books.*
import me.wverdese.example.myreadinglist.MyReadingListApplication
import me.wverdese.example.myreadinglist.R
import me.wverdese.example.myreadinglist.databinding.ActivityBooksBinding
import me.wverdese.example.myreadinglist.ui.BooksState.*
import me.wverdese.example.myreadinglist.ui.BooksState.ShowToast.BookTapped
import me.wverdese.example.myreadinglist.ui.BooksState.ShowToast.NoItems
import me.wverdese.example.myreadinglist.ui.item.BookItem
import me.wverdese.example.myreadinglist.ui.item.HeaderItem
import me.wverdese.example.myreadinglist.util.showToast
import javax.inject.Inject


class BooksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksBinding
    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    @Inject lateinit var booksViewModelFactory: BooksViewModelFactory

    private val viewModel by viewModels<BooksViewModel> { booksViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (application as MyReadingListApplication).component.inject(this)

        with(binding) {
            groupAdapter = GroupAdapter()
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = groupAdapter
            }

            swipeRefreshView.setOnRefreshListener { viewModel.fetchData() }
        }

        viewModel.state().observe(this, Observer { state ->
            updateIsRefreshing(state.isRefreshing)
            updateList(state.bookItemStates)
            handleShowToast(state.showToast)
        })

        viewModel.fetchData()
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        swipeRefreshView.isRefreshing = isRefreshing
    }

    private fun updateList(books: BooksItemStatesByYear) {
        groupAdapter.clear()
        books.forEach {
            val section = Section()
            section.setHeader(HeaderItem(it.key))
            val items = it.value.map { state -> BookItem(state) }
            section.addAll(items)
            groupAdapter.add(section)
        }
    }

    private fun handleShowToast(showToast: ShowToast?) {
        showToast?.doIfNotHandled {
            when (showToast) {
                is NoItems -> showToast(getString(R.string.show_toast_no_items))
                is BookTapped -> showToast(getString(R.string.show_toast_book_detail, showToast.bookId))
            }
        }
    }
}
