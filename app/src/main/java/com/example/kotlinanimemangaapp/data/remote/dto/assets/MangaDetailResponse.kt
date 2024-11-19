package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.data.remote.dto.assets.AnimeDto
import com.squareup.moshi.Json

data class AnimeDetailResponse(
    @Json(name = "data")
    val `data`: AnimeDto
)