package me.wverdese.example.myreadinglist.data

import android.util.Log
import me.wverdese.example.myreadinglist.data.api.RemoteBooksApi
import me.wverdese.example.myreadinglist.data.model.Book
import me.wverdese.example.myreadinglist.data.model.BookMapper
import me.wverdese.example.myreadinglist.data.storage.LocalBookDao
import java.lang.IllegalStateException
import javax.inject.Inject

class BooksService @Inject constructor(
    private val remoteBooksApi: RemoteBooksApi,
    private val localBookDao: LocalBookDao,
    private val bookMapper: BookMapper
) {

    suspend fun fetchBooks(): List<Book> {
        try {
            val remoteBooks = remoteBooksApi.fetchRemoteBooks()
            val localBooks = remoteBooks.books.map { bookMapper.remoteToLocal(it) }
            localBookDao.insertAll(localBooks)
        } catch (t: Throwable) {
            Log.e(TAG, "error while synchronizing books", t) //log and swallow
        } finally {
            val localBooks = localBookDao.getAll()
            if (localBooks.isEmpty()) throw IllegalStateException("db is empty")
            return localBooks.map { bookMapper.localToModel(it) }
        }
    }

    companion object {
        const val TAG = "BooksService"
    }
}
