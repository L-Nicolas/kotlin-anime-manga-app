package com.example.kotlinanimemangaapp.presentation.screens.user_liste_animes

import com.example.kotlinanimemangaapp.domain.model.ListAnime

data class UserAnimeListState(
    val isLoading: Boolean = false,
    val animes: List<ListAnime> = emptyList(),
    val error: String = ""
)

data class UserAnimeListFavoriteState(
    val isLoading: Boolean = false,
    val animes: List<ListAnime> = emptyList(),
    val error: String = ""
)

data class UserAnimeListWatchedState(
    val isLoading: Boolean = false,
    val animes: List<ListAnime> = emptyList(),
    val error: String = ""
)