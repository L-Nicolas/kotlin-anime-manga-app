package com.example.kotlinanimemangaapp.presentation.screens.user_list_animes_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinanimemangaapp.common.Constants
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.AnimeInteractor
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteListUC
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteAnimeFromListUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.update_shared_list.UpdateSharedListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserAnimeListDetailViewModel  @Inject constructor(
    private val interactor: AnimeInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateListAnime = mutableStateOf(UserAnimeListDetailState())
    val stateListAnime: State<UserAnimeListDetailState> = _stateListAnime

    init {
        savedStateHandle.get<String>(Constants.PARAM_LIST_ID)?.let { id ->
            getListAnimeOfUser(id)
        }
    }

    private fun getListAnimeOfUser(idList: String) {
        interactor.getListAnimeOfUserUC(idList).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListAnime.value = UserAnimeListDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListAnime.value = UserAnimeListDetailState(animeListDetail = resource.data)
                }
                is Resource.Error -> {
                    _stateListAnime.value = UserAnimeListDetailState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun changeSharedList(listId: String) {
        interactor.updateSharedListUC(listId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    _stateListAnime.value = UserAnimeListDetailState(animeListDetail = _stateListAnime.value.animeListDetail?.copy(shared = !_stateListAnime.value.animeListDetail!!.shared))
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteAnimeFromList(idList: String, idAnime: Int) {
        interactor.deleteAnimeFromListUC(idList, idAnime).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    getListAnimeOfUser(idList)
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteList(idList: String) {
        interactor.deleteListUC(idList).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    _stateListAnime.value = UserAnimeListDetailState(animeListDetail = null)
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)
    }
}