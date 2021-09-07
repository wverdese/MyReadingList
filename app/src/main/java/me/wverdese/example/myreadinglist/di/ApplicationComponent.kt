package me.wverdese.example.myreadinglist.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.wverdese.example.myreadinglist.data.api.RemoteBooksApiModule
import me.wverdese.example.myreadinglist.db.RoomDatabaseModule
import me.wverdese.example.myreadinglist.ui.BooksActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RoomDatabaseModule::class,
        RemoteBooksApiModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: BooksActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
