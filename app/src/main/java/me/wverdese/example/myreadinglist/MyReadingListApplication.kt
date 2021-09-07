package me.wverdese.example.myreadinglist

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import me.wverdese.example.myreadinglist.di.ApplicationComponent
import me.wverdese.example.myreadinglist.di.DaggerApplicationComponent

class MyReadingListApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this);
    }
}
