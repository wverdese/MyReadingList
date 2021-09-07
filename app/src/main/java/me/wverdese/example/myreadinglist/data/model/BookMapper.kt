package me.wverdese.example.myreadinglist.data.model

import me.wverdese.example.myreadinglist.data.api.RemoteBook
import me.wverdese.example.myreadinglist.data.storage.LocalBook
import org.threeten.bp.LocalDate
import javax.inject.Inject


class BookMapper @Inject constructor() {

    fun localToModel(localBook: LocalBook) =
        with(localBook) {
            Book(
                id = id,
                title = title,
                author = author,
                publishDate = LocalDate.parse(publishDate),
                coverImageUrl = coverImageUrl
            )
        }

    fun remoteToLocal(remoteBook: RemoteBook) =
        with(remoteBook) {
            LocalBook(
                id = id,
                title = title,
                author = author,
                publishDate = publishDate,
                coverImageUrl = coverImageUrl
            )
        }
}
