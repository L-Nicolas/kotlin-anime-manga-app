package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.To
import com.squareup.moshi.Json

data class ToDto(
    @Json(name = "day")
    val day: Int?,
    @Json(name = "month")
    val month: Int?,
    @Json(name = "year")
    val year: Int?
)

fun ToDto.toTo() : To = To(
    day = day,
    month = month,
    year = year
)
