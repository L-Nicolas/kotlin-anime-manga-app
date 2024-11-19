package com.example.kotlinanimemangaapp.domain.model

data class Pagination (
    val hasNextPage: Boolean,
    val items: Items,
    val lastVisiblePage: Int
)