package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Book(
    val id: String = "",
    @SerialName("kind") val kind: String = "",
    @SerialName("volumeInfo") val volumeInfo: VolumeInfo = VolumeInfo()
)

@Serializable
data class VolumeInfo(
    @SerialName("title") val title: String = "Unknown title",
    @SerialName("imageLinks") val imageLinks: ImageLinks = ImageLinks(),
    @SerialName("description") val description: String = "No Description",
    @SerialName("publisher") val publisher: String = "Unknown",
    @SerialName("authors") val authors: List<String> = listOf("Author", "Unknown"),
    @SerialName("publishedDate") val publishedDate: String = "Unknown",
    @SerialName("pageCount") val pageCount: Int = 0,
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail") val thumbnail: String = "no image",
    @SerialName("smallThumbnail") val smallThumbnail: String = "no image",
    @SerialName("small") val small: String = "no image",
    @SerialName("medium") val medium: String = "no image",
    @SerialName("large") val large: String = "no image",
    @SerialName("extraLarge") val extraLarge: String = "no image"
)

