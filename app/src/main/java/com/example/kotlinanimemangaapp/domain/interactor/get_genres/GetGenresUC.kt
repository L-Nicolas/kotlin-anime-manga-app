package com.example.kotlinanimemangaapp.domain.interactor.get_genres

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetGenresUC @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(): Flow<Resource<List<Genre>>> = flow {
        val genres = animeRepository.getGenres()
        if (genres.isEmpty()) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(genres))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}