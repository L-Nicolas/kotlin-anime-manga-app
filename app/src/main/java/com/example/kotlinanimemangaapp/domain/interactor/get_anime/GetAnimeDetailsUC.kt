package com.example.kotlinanimemangaapp.domain.interactor.get_anime

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.AnimeDetail
import com.example.kotlinanimemangaapp.domain.repository.AnimeDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetAnimeDetailsUC @Inject constructor(
    private val animeDetailRepository: AnimeDetailRepository
) {
    operator fun invoke(animeId: Int): Flow<Resource<AnimeDetail>> = flow {
        val animeDetail = animeDetailRepository.getAnimeDetail(animeId)
        if (animeDetail == null) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(animeDetail))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}