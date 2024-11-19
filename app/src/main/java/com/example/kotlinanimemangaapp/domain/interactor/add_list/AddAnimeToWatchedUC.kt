package com.example.kotlinanimemangaapp.domain.interactor.add_list

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddAnimeToWatchedUC @Inject constructor(
    private val listAnimeRepository: ListAnimeRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Unit>> = flow {
        val result = listAnimeRepository.addAnimeToWatched(id)
        emit(result)
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}