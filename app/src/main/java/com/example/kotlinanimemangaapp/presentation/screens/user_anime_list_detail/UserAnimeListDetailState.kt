package com.example.kotlinanimemangaapp.presentation.screens.user_list_animes_detail

import com.example.kotlinanimemangaapp.domain.model.AnimeListDetail

data class UserAnimeListDetailState(
    val isLoading: Boolean = false,
    val animeListDetail: AnimeListDetail? = null,
    val error: String = ""
)