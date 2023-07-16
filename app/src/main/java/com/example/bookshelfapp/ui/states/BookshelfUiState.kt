package com.example.bookshelfapp.ui.states

import com.example.bookshelfapp.model.Book

data class BookshelfUiState(
    val currentSearch: String = "jazz+history",
    val isShowingHomepage: Boolean = true,
    val currentBook: Book = Book()
    )