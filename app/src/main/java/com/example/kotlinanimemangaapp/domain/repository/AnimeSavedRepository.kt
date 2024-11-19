package com.example.kotlinanimemangaapp.domain.repository

import com.example.kotlinanimemangaapp.common.Resource

interface AnimeSavedRepository {
    suspend fun addAnimeToFavorite(id: Int): Resource<Unit>
}