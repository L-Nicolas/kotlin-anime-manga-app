package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "data")
    val `data`: List<GenreDto>
)