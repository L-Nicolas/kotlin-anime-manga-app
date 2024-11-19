package com.example.kotlinanimemangaapp.presentation.screens.anime_list

import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.domain.model.Anime

data class AnimeListState(
    val isLoading: Boolean = false,
    val animes: List<Anime> = emptyList(),
    val error: String = ""
)

data class ListGenresState(
    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val error: String = ""
)