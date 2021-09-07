package me.wverdese.example.myreadinglist.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "myreadinglist.db"

@Module
class RoomDatabaseModule {

  @Provides @Singleton fun getRoomDb(context: Context) =
      Room.databaseBuilder(context, RoomDatabase::class.java, DB_NAME).build()

  @Provides fun getLocalBokDao(roomDatabase: RoomDatabase) = roomDatabase.localBookDao()
}
