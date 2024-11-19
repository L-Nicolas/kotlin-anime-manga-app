package com.example.kotlinanimemangaapp.presentation.screens.user_liste_animes

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.AnimeInteractor
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddListUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListFavoriteAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeWatchedOfUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserAnimeListViewModel @Inject constructor(
    private val interactor: AnimeInteractor,
) : ViewModel() {

    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }

    fun onAddListClick(text: String){
        addList(text)
        getListAnimeOfUser()
        onDismissDialog()
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _stateListAnime = mutableStateOf(UserAnimeListState())
    val stateListAnime: State<UserAnimeListState> = _stateListAnime

    private val _stateListFavorite = mutableStateOf(UserAnimeListFavoriteState())
    val stateListFavorite: State<UserAnimeListFavoriteState> = _stateListFavorite

    private val _stateAnimeWatched = mutableStateOf(UserAnimeListWatchedState())
    val stateAnimeWatched: State<UserAnimeListWatchedState> = _stateAnimeWatched

    init {
        loadListAnimeOfUser()
    }

    fun loadListAnimeOfUser() {
        getListAnimeOfUser()
        getListAnimeWatchedOfUser()
        getListFavoriteAnimeOfUser()
    }


    private fun getListAnimeOfUser() {
        interactor.getListAnimeOfUserUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListAnime.value = UserAnimeListState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListAnime.value = UserAnimeListState(animes = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateListAnime.value = UserAnimeListState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getListAnimeWatchedOfUser() {
        interactor.getListAnimeWatchedOfUserUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateAnimeWatched.value = UserAnimeListWatchedState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateAnimeWatched.value = UserAnimeListWatchedState(animes = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateAnimeWatched.value = UserAnimeListWatchedState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getListFavoriteAnimeOfUser() {
        interactor.getListFavoriteAnimeOfUserUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListFavorite.value = UserAnimeListFavoriteState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListFavorite.value = UserAnimeListFavoriteState(animes = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateListFavorite.value = UserAnimeListFavoriteState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addList(title: String) {
        interactor.addListUC(title).onEach { resource ->
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

}
