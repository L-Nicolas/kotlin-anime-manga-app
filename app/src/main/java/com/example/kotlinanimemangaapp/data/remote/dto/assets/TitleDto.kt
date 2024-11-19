package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Title
import com.squareup.moshi.Json

data class TitleDto(
    @Json(name = "title")
    val title: String,
    @Json(name = "type")
    val type: String
)

fun TitleDto.toTitle() : Title = Title(
    title = title,
    type = type
)