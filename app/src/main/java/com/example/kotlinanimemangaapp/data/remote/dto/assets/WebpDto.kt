package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Webp
import com.squareup.moshi.Json

data class WebpDto(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "large_image_url")
    val largeImageUrl: String,
    @Json(name = "small_image_url")
    val smallImageUrl: String
)

fun WebpDto.toWebp() : Webp = Webp(
    imageUrl = imageUrl,
    largeImageUrl = largeImageUrl,
    smallImageUrl = smallImageUrl
)