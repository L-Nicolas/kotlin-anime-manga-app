package com.example.kotlinanimemangaapp.presentation.screens.anime_list

sealed class AnimeListScreenEvent {
    object OnRefresh : AnimeListScreenEvent()
}