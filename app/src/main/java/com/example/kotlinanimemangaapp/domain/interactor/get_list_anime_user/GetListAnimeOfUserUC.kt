package com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.model.AnimeListDetail
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetListAnimeOfUserUC @Inject constructor(
    private val listAnimeRepository: ListAnimeRepository
) {
    operator fun invoke(): Flow<Resource<List<ListAnime>>> = flow {
        val list = listAnimeRepository.getListAnimeOfUser()
        if (list.isEmpty()) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(list))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }

    operator fun invoke(idList: String): Flow<Resource<AnimeListDetail>> = flow {
        val list = listAnimeRepository.getListAnimeOfUser(idList)
        if (list == null) {
            emit(Resource.Error(message = "No data found"))
        } else {
            emit(Resource.Success(list))
        }
    }.onStart { emit(Resource.Loading()) }
        .catch { e -> emit(Resource.Error(message = "Error with ${e.localizedMessage}")) }
}
