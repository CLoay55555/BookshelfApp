package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    @SerialName("items")val bookIds: List<BookId>
)

@Serializable
data class BookId(
    @SerialName("id") val id: String
)

