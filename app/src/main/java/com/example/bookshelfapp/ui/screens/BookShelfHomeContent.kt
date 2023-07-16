package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.ui.utils.BookshelfContentType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCover(
    book: Book,
    onBookCoverClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    val imgSrc = book.volumeInfo.imageLinks.thumbnail.replace("http", "https")

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = onBookCoverClicked
    ) {

        AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgSrc)
            .crossfade(true)
            .build()
            ,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_connection_error),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier.fillMaxWidth()
        )
    }
}





@Composable
fun BooksGrid(books: List<Book>, onBookCoverClicked: (Book) -> Unit, contentType: BookshelfContentType, modifier: Modifier = Modifier){
    if(contentType == BookshelfContentType.LIST_ONLY) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(items = books, key = { book -> book.id }) { book ->
                BookCover(
                    book,
                    onBookCoverClicked = { onBookCoverClicked(book) },
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(.5f)
                )

            }
        }
    }else{
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(items = books, key = { book -> book.id }) { book ->
                BookCover(
                    book,
                    onBookCoverClicked = { onBookCoverClicked(book) },
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(.5f)
                )

            }
        }
    }
}
