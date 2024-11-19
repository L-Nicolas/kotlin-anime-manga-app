package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Pagination
import com.squareup.moshi.Json

data class PaginationDto(
    @Json(name = "has_next_page")
    val hasNextPage: Boolean,
    @Json(name = "items")
    val itemsDto: ItemsDto,
    @Json(name = "last_visible_page")
    val lastVisiblePage: Int
)

fun PaginationDto.toPagination() : Pagination = Pagination(
    hasNextPage = hasNextPage,
    items = itemsDto.toItems(),
    lastVisiblePage = lastVisiblePage
)