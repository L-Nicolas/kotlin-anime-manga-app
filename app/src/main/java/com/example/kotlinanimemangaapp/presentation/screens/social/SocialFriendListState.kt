package com.example.kotlinanimemangaapp.presentation.screens.social

import com.example.kotlinanimemangaapp.domain.model.Friend
import com.example.kotlinanimemangaapp.domain.model.ListAnime

data class SocialFriendListState(
    val isLoading: Boolean = false,
    val friends: List<Friend> = emptyList(),
    val error: String = ""
)


data class UserAnimeListSharedState(
    val isLoading: Boolean = false,
    val animes: List<ListAnime> = emptyList(),
    val error: String = ""
)
