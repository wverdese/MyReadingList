package me.wverdese.example.myreadinglist.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Book")
data class LocalBook(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "publish_date")
    val publishDate: String,
    @ColumnInfo(name = "cover_img_url")
    val coverImageUrl: String
)
