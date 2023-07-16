package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.ui.screens.BookshelfViewModel
import com.example.bookshelfapp.ui.screens.HomeScreen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BookShelfApp(windowSize: WindowWidthSizeClass){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    val bookshelfUiState = bookshelfViewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BookshelfTopAppBar(scrollBehavior = scrollBehavior,)}
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(modifier = Modifier.padding(4.dp)) {
                HomeScreen(
                    bookshelfDataState = bookshelfViewModel.bookshelfDataState,
                    onBookCoverClicked = { book: Book ->
                        bookshelfViewModel.updateDetailsScreenStates(book = book)
                    },
                    windowSize = windowSize,
                    bookshelfUiState = bookshelfUiState,
                    onBackButtonClicked = {bookshelfViewModel.resetScreenStates()},
                    onUserSearchChange = bookshelfViewModel::updateUserSearch,
                    onSearchButtonClick = bookshelfViewModel::getGoogleBooks
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        modifier = modifier
    )
}






