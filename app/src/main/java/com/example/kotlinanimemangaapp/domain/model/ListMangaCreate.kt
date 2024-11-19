package com.example.kotlinanimemangaapp.domain.model

data class ListAnimeCreate(
    val animes: List<Anime> =  emptyList(),
    val name: String,
    val id_user: String,
    val shared: Boolean = false,
)
