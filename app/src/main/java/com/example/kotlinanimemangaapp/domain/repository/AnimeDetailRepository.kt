package com.example.kotlinanimemangaapp.domain.repository

import com.example.kotlinanimemangaapp.domain.model.AnimeDetail

interface AnimeDetailRepository  {
    suspend fun getAnimeDetail(idAnime: Int): AnimeDetail?
}