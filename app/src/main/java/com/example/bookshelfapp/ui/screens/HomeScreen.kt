package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.ui.states.BookshelfUiState
import com.example.bookshelfapp.ui.utils.BookshelfContentType


@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    bookshelfDataState: BookshelfDataState,
    onBookCoverClicked: (Book) -> Unit,
    windowSize: WindowWidthSizeClass,
    onBackButtonClicked: () -> Unit,
    onSearchButtonClick: () -> Unit,
    onUserSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val contentType: BookshelfContentType
    when(windowSize){
        WindowWidthSizeClass.Compact ->{
            contentType = BookshelfContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium ->{
            contentType = BookshelfContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded ->{
            contentType = BookshelfContentType.LIST_AND_DETAIL
        }
        else ->{
            contentType = BookshelfContentType.LIST_ONLY
        }
    }

        when (bookshelfDataState) {
            is BookshelfDataState.Loading -> LoadingScreen()
            is BookshelfDataState.Error -> ErrorScreen()
            is BookshelfDataState.Success -> BookshelfHomeScreen(
                books = bookshelfDataState.books,
                onBookCoverClicked = onBookCoverClicked,
                contentType = contentType,
                bookshelfUiState = bookshelfUiState,
                onBackButtonClicked = onBackButtonClicked,
                onSearchButtonClick = onSearchButtonClick,
                onUserSearchChange = onUserSearchChange
            )

        }

}

@Composable
fun BookshelfHomeScreen(
    bookshelfUiState: BookshelfUiState,
    books: List<Book>,
    onBookCoverClicked: (Book) -> Unit,
    onBackButtonClicked: () -> Unit,
    contentType: BookshelfContentType,
    modifier: Modifier = Modifier,
    onSearchButtonClick: () -> Unit,
    onUserSearchChange: (String) -> Unit,
){
    Column() {
        SearchBar(onUserSearchChange = { onUserSearchChange(it) },
            userSearch = bookshelfUiState.currentSearch,
            onSearchButtonClick = { onSearchButtonClick() })
        if (contentType == BookshelfContentType.LIST_ONLY && bookshelfUiState.isShowingHomepage) {

            BooksGrid(books = books, onBookCoverClicked = onBookCoverClicked, contentType)
        } else {
            BookDetails(
                book = bookshelfUiState.currentBook,
                modifier = modifier.fillMaxSize(),
                onBackButtonClicked = { onBackButtonClicked() }
            )
        }
    }
    
}



@Composable
fun LoadingScreen(){
    Text(text = "Loading")
}

@Composable
fun ErrorScreen(){
    Text(text = "Error")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchButtonClick: () -> Unit,
    userSearch: String,
    onUserSearchChange: (String) -> Unit,
){
    TextField(
        value = userSearch,
        shape = MaterialTheme.shapes.large,
        onValueChange = {onUserSearchChange(it)},
        keyboardOptions = KeyboardOptions.Default.copy( imeAction = ImeAction.Search),
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = {onSearchButtonClick()})
        )
}
