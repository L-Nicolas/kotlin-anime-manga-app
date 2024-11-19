package com.example.kotlinanimemangaapp.domain.model

data class AnimeListDetail(
    var animes: List<Anime>,
    var title: String,
    var id_user: String,
    var shared : Boolean,
    var id: String
)
