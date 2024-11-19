package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.data.remote.dto.assets.AnimeDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.PaginationDto
import com.squareup.moshi.Json

data class AnimeResponse(
    @Json(name = "data")
    val `data`: List<AnimeDto>,
    @Json(name = "pagination")
    val paginationDto: PaginationDto
)
