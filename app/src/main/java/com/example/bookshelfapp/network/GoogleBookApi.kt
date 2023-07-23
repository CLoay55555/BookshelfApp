package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.VolumeInfo
import com.example.bookshelfapp.model.Books
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface GoogleBookApiService{



        @GET("books/v1/volumes")
        suspend fun getBooks(@Query("q") query: String): Books

        @GET("books/v1/volumes/{volume}")
        suspend fun getBookData(@Path("volume")volumeKey: String): Book

}
