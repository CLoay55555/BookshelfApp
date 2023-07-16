package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.Book


@Composable
fun BookDetails(
    book: Book,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier){


    LazyColumn() {
        item {
           BookDetailsTopBar(onBackButtonClicked = {onBackButtonClicked()})
            Card(modifier = modifier) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(12.dp)
                ) {

                    BookDetailsHeaderWithImage(book = book, modifier = Modifier.aspectRatio(1f))
                    BookDetailsBody(book = book)
                }

            }
        }
    }
}

@Composable
fun BookDetailsHeaderWithImage(book: Book, modifier: Modifier = Modifier){
    val imgSrc =  book.volumeInfo.imageLinks.thumbnail.replace("http", "https")
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Color.White)
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_connection_error),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .weight(1f),


            )
            Text(
                text = stringResource(R.string.booktitle)+book.volumeInfo.title,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
            Text(text = stringResource(R.string.author)+book.volumeInfo.authors)
            Text(text = stringResource(R.string.publisher)+ book.volumeInfo.publisher)
            Text(text = stringResource(R.string.publishDate)+book.volumeInfo.publishedDate)
            Text(text = stringResource(R.string.pageCount)+"${book.volumeInfo.pageCount}")

        }

}



@Composable
fun BookDetailsBody(book: Book, modifier: Modifier = Modifier){

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = book.volumeInfo.description,
            textAlign = TextAlign.Justify,
            fontSize = 28.sp
        )
    }

}

@Composable
fun BookDetailsTopBar(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    IconButton(
        onClick = onBackButtonClicked,
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.navigation_back)
        )
    }

}