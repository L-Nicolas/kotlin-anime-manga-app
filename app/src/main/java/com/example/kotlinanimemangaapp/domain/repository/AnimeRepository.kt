package com.example.kotlinanimemangaapp.domain.repository

import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.domain.model.Anime

interface AnimeRepository {
    suspend fun getAnime(animeId: Int): Anime?
    suspend fun getAnime(searchText: String, listIdGenre: List<Int>?): List<Anime>
    suspend fun getGenres(): List<Genre>
}