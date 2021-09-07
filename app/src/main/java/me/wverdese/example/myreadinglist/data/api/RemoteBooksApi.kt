package me.wverdese.example.myreadinglist.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

const val API_BASE_URL = "https://us-central1-mockbooks-22af5.cloudfunctions.net/api/"

interface RemoteBooksApi {

    @GET("books")
    suspend fun fetchRemoteBooks(): RemoteBooks

    class Factory @Inject constructor() {

        fun create(): RemoteBooksApi =
            Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor()
                                .setLevel(Level.BODY)
                        )
                        .build()
                )
                .build()
                .create(RemoteBooksApi::class.java)
    }
}
