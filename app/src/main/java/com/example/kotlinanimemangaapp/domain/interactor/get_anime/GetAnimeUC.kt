package com.example.kotlinanimemangaapp.domain.interactor.get_anime

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetAnimeUC @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(idAnime: Int): Flow<Resource<Anime>> = flow {
        val anime = animeRepository.getAnime(idAnime)
        if (anime == null) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(anime))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }

    operator fun invoke(searchText: String = "", listIdGenre: List<Int>?): Flow<Resource<List<Anime>>> = flow {
        val animes = animeRepository.getAnime(searchText, listIdGenre)
        if (animes.isEmpty()) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(animes))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}
