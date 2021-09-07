package me.wverdese.example.myreadinglist.data.api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class RemoteBooksApiModule {

    @Provides @Singleton fun providesBooksApi(factory: RemoteBooksApi.Factory): RemoteBooksApi =
        factory.create()
}
