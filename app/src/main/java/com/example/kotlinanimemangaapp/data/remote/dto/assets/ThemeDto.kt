package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Theme
import com.squareup.moshi.Json

data class ThemeDto(
    @Json(name = "mal_id")
    val malId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

fun ThemeDto.toTheme() : Theme = Theme(
    malId = malId,
    name = name,
    type = type,
    url = url
)