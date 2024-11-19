package com.example.kotlinanimemangaapp.domain.interactor.add_list

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AddAnimeToListUC @Inject constructor(
    private val listAnimeRepository: ListAnimeRepository
) {
    operator fun invoke(idAnime: Int, idList: String): Flow<Resource<Unit>> = flow {
        val result = listAnimeRepository.addAnimeToList(idAnime, idList)
        emit(result)
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}