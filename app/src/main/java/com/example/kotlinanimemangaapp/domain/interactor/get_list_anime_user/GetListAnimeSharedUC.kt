package com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetListAnimeShareddUC @Inject constructor(
    private val listAnimeRepository: ListAnimeRepository
) {
    operator fun invoke(): Flow<Resource<List<ListAnime>>> = flow {
        val friends = listAnimeRepository.getListAnimeShared()
        if (friends.isEmpty()) {
            emit(Resource.Error(message = "Aucune liste partagée"))
        } else {
            emit(Resource.Success(friends))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}
