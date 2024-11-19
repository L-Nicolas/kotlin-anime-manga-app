package com.example.kotlinanimemangaapp.data.repository

import com.example.kotlinanimemangaapp.data.remote.api.AnimeApi
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.domain.repository.AnimeRepository
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toAnime
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toGenre
import javax.inject.Inject

class AnimeDatasource @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {

    override suspend fun getAnime(idAnime: Int): Anime? = animeApi.getAnime(idAnime).data.toAnime()

    override suspend fun getAnime(searchText: String, listIdGenre: List<Int>?): List<Anime> = animeApi.getAnime(searchText = searchText, listIdGenre = listIdGenre).data.map {
        it.toAnime()
    }

    override suspend fun getGenres(): List<Genre> = animeApi.getGenres().data?.map {
        it.toGenre()
    } ?: emptyList()

}
