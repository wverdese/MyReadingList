package me.wverdese.example.myreadinglist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.wverdese.example.myreadinglist.data.storage.LocalBook
import me.wverdese.example.myreadinglist.data.storage.LocalBookDao

@Database(
  entities = [
    LocalBook::class
  ],
  version = 1
)
abstract class RoomDatabase : RoomDatabase() {

  abstract fun localBookDao(): LocalBookDao
}
