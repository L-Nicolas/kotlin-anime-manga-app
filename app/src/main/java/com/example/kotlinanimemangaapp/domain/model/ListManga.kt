package com.example.kotlinanimemangaapp.domain.model

data class ListAnime(
    val anime: List<Int> = emptyList(),
    val name: String,
    val id_user: String,
    val id: String
)