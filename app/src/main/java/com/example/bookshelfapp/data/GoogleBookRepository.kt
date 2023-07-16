package com.example.bookshelfapp.data

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookId
import com.example.bookshelfapp.model.VolumeInfo
import com.example.bookshelfapp.network.GoogleBookApiService


interface GoogleBookRepository{
    suspend fun getGoogleBooks(userSearch: String):  List<Book>
    suspend fun getBookData(id: String): Book

}

class NetworkGoogleBookRepository(private val googleBookApiService: GoogleBookApiService): GoogleBookRepository{

    override suspend fun getGoogleBooks(userSearch: String): List<Book> {
        val response = googleBookApiService.getBooks(userSearch)
        val books: List<BookId> = response.bookIds
        return books.map{getBookData(it.id)}
    }

    override suspend fun getBookData(id: String): Book = googleBookApiService.getBookData(id)


}