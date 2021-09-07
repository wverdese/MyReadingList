package me.wverdese.example.myreadinglist.data

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import me.wverdese.example.myreadinglist.data.api.RemoteBook
import me.wverdese.example.myreadinglist.data.api.RemoteBooks
import me.wverdese.example.myreadinglist.data.api.RemoteBooksApi
import me.wverdese.example.myreadinglist.data.model.Book
import me.wverdese.example.myreadinglist.data.model.BookMapper
import me.wverdese.example.myreadinglist.data.storage.LocalBook
import me.wverdese.example.myreadinglist.data.storage.LocalBookDao
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class BooksServiceShould {

    @Mock
    private lateinit var remoteBooksApi: RemoteBooksApi
    @Mock
    private lateinit var localBookDao: LocalBookDao
    @Mock
    private lateinit var bookMapper: BookMapper

    @InjectMocks
    private lateinit var booksService: BooksService

    private val remoteBooks: RemoteBooks = mock()
    private val remoteBook: RemoteBook = mock()
    private val localBook: LocalBook = mock()
    private val book: Book = mock()

    @Before
    fun setup() =
        runBlockingTest {
            whenever(remoteBooksApi.fetchRemoteBooks()).thenReturn(remoteBooks)
            whenever(remoteBooks.books).thenReturn(listOf(remoteBook))
            whenever(localBookDao.getAll()).thenReturn(listOf(localBook))
            whenever(bookMapper.remoteToLocal(remoteBook)).thenReturn(localBook)
            whenever(bookMapper.localToModel(localBook)).thenReturn(book)
        }

    @Test
    fun `fetch books from remote api`() =
        runBlockingTest {
            booksService.fetchBooks()

            verify(remoteBooksApi).fetchRemoteBooks()
        }

    @Test
    fun `map remote books to local`() =
        runBlockingTest {
            booksService.fetchBooks()

            verify(bookMapper).remoteToLocal(eq(remoteBook))
        }

    @Test
    fun `store local books in db`() =
        runBlockingTest {
            booksService.fetchBooks()

            verify(localBookDao).insertAll(eq(listOf(localBook)))
        }

    @Test
    fun `fetch local books from db`() =
        runBlockingTest {
            booksService.fetchBooks()

            verify(localBookDao).getAll()
        }

    @Test
    fun `return list of books`() =
        runBlockingTest {
            val books = booksService.fetchBooks()

            assert(books == listOf(book))
        }

    @Test(expected = RuntimeException::class)
    fun `swallow exception when fetching from remote`() =
        runBlockingTest {
            whenever(booksService.fetchBooks()).thenThrow(RuntimeException())

            booksService.fetchBooks()

            verify(localBookDao).getAll()
        }

    @Test(expected = IllegalStateException::class)
    fun `propagate exception when db is empty`() =
        runBlockingTest {
            whenever(localBookDao.getAll()).thenReturn(emptyList())
            booksService.fetchBooks()
        }
}

