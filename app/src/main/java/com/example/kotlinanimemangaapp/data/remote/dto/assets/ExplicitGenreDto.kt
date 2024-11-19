package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.ExplicitGenre
import com.squareup.moshi.Json

data class ExplicitGenreDto(
    @Json(name = "mal_id")
    val malId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

fun ExplicitGenreDto.toExplicitGenre() : ExplicitGenre = ExplicitGenre(
    malId = malId,
    name = name,
    type = type,
    url = url
)