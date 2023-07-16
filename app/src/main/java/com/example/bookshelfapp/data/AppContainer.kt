package com.example.bookshelfapp.data

import com.example.bookshelfapp.network.GoogleBookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer{
    val googleBookRepository: GoogleBookRepository
}

class DefaultAppContainer : AppContainer{

    private val BASEURL = "https://www.googleapis.com/"

    val json = Json{
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASEURL)
        .build()

    private val retrofitService: GoogleBookApiService by lazy{
        retrofit.create(GoogleBookApiService::class.java)
    }

    override val googleBookRepository: GoogleBookRepository by lazy{
        NetworkGoogleBookRepository(retrofitService)
    }

}