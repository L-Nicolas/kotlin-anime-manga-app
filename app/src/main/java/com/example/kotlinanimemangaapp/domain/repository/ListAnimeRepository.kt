package com.example.kotlinanimemangaapp.domain.repository

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.model.AnimeListDetail

interface ListAnimeRepository {
    suspend fun getListAnimeShared(): List<ListAnime>

    suspend fun getListAnimeOfUser(): List<ListAnime>

    suspend fun getListAnimeOfUser(idList: String): AnimeListDetail

    suspend fun getListFavoriteAnimeOfUser(): List<ListAnime>

    suspend fun getListAnimeWatchedOfUser(): List<ListAnime>

    suspend fun shareList(id: String): Resource<Unit>

    suspend fun addList(title: String): Resource<Unit>

    suspend fun addAnimeToList(idAnime: Int, idList: String): Resource<Unit>

    suspend fun addAnimeToWatched(idAnime: Int): Resource<Unit>

    suspend fun deleteList(idList: String): Resource<Unit>

    suspend fun deleteAnimeFromList(idList: String, idAnime: Int): Resource<Unit>
}