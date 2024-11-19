package com.example.kotlinanimemangaapp.presentation.screens.anime_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinanimemangaapp.common.Constants
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.AnimeInteractor
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToFavoriteUC
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToListUC
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToWatchedUC
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteAnimeFromListUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.get_anime.GetAnimeDetailsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val interactor: AnimeInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = mutableStateOf(AnimeDetailState())
    val state: State<AnimeDetailState> = _state

    private val _stateListAnime = mutableStateOf(AnimeListDetailState())
    val stateListAnime: State<AnimeListDetailState> = _stateListAnime

    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick(){
        if(!_stateListAnime.value.list.isEmpty()) {
            isDialogShown = true
        }
    }

    fun onDismissDialog(){
        isDialogShown = false
    }

    init {
        savedStateHandle.get<Int>(Constants.PARAM_ANIME_ID)?.let { animeId ->
            getAnime(animeId)
            getListAnimeOfUser()
        }
    }

    /*fun addAnimeList(animeList: List<ListAnime>) {
        _animeLists.value = _animeLists.value + listOf(animeList)
    }

    fun removeAnimeList(animeList: List<ListAnime>) {
        _animeLists.value = _animeLists.value - listOf(animeList)
    }*/

    fun addAnimeToAnimeList(idAnime: Int, idList: String) {
        /*val updatedList = animeList + listOf(listAnime)
        _animeLists.value = _animeLists.value.toMutableList().apply {
            set(indexOf(animeList), updatedList)
        }*/
        addAnimeToList(idAnime, idList)
    }

    fun removeAnimeFromAnimeList(idAnime: Int, idList: String) {
        /*val updatedList = animeList - listOf(listAnime)
        _animeLists.value = _animeLists.value.toMutableList().apply {
            set(indexOf(animeList), updatedList)
        }*/
        deleteAnimeFromList(idAnime, idList)
    }

    private fun addAnimeToList(idAnime: Int, idList: String) {
        interactor.addAnimeToListUC(idAnime, idList).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error ${resource.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteAnimeFromList(idAnime: Int, idList: String) {
        interactor.deleteAnimeFromListUC(idList, idAnime).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAnime(animeId: Int) {
        interactor.getAnimeDetailsUC(animeId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = AnimeDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = AnimeDetailState(anime = resource.data)
                }
                is Resource.Error -> {
                    _state.value = AnimeDetailState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addAnimeToFavorite(animeId: Int) {
        interactor.addAnimeToFavoriteUC(animeId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addAnimeToWatch(animeId: Int) {
        interactor.addAnimeToWatchedUC(animeId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error")
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getListAnimeOfUser() {
        interactor.getListAnimeOfUserUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListAnime.value = AnimeListDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListAnime.value = AnimeListDetailState(list = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateListAnime.value = AnimeListDetailState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}