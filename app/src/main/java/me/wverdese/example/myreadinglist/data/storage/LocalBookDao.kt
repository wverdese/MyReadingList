package me.wverdese.example.myreadinglist.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LocalBookDao {
    @Query("SELECT * FROM Book")
    suspend fun getAll(): List<LocalBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<LocalBook>)
}
