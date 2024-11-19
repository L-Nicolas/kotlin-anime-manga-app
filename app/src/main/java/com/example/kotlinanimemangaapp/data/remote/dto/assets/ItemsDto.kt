package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.domain.model.Items
import com.squareup.moshi.Json

data class ItemsDto(
    @Json(name = "count")
    val count: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "total")
    val total: Int
)

fun ItemsDto.toItems() : Items = Items(
    count = count,
    perPage = perPage,
    total = total
)