package com.example.kotlinanimemangaapp.presentation.screens.anime_detail

import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.model.AnimeDetail

data class AnimeDetailState(
    val isLoading: Boolean = false,
    val anime: AnimeDetail? = null,
    val error: String = ""
)
data class AnimeListDetailState(
    val isLoading: Boolean = false,
    val list: List<ListAnime> = emptyList(),
    val error: String = ""
)