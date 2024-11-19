package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.From
import com.squareup.moshi.Json

data class FromDto(
    @Json(name = "day")
    val day: Int?,
    @Json(name = "month")
    val month: Int?,
    @Json(name = "year")
    val year: Int?
)

fun FromDto.toFrom(): From = From(
    day = day,
    month = month,
    year = year
)