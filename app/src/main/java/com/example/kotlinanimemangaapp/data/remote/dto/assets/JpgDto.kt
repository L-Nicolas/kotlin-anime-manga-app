package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Jpg
import com.squareup.moshi.Json

data class JpgDto(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "large_image_url")
    val largeImageUrl: String,
    @Json(name = "small_image_url")
    val smallImageUrl: String
)

fun JpgDto.toJpg() : Jpg = Jpg(
    imageUrl = imageUrl,
    largeImageUrl = largeImageUrl,
    smallImageUrl = smallImageUrl
)