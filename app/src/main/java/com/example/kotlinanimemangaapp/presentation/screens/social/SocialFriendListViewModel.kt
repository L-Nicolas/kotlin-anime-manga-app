package com.example.kotlinanimemangaapp.presentation.screens.social

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.AnimeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SocialFriendListViewModel @Inject constructor(
    private val interactor: AnimeInteractor,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _stateListAnime = mutableStateOf(UserAnimeListSharedState())
    val stateListAnime: State<UserAnimeListSharedState> = _stateListAnime

    init {
        loadFriends()
    }

    fun loadFriends() {
        getListAnimeShared()
    }

    private fun getListAnimeShared() {
        interactor.getListAnimeShareddUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListAnime.value = UserAnimeListSharedState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListAnime.value = UserAnimeListSharedState(animes = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateListAnime.value = UserAnimeListSharedState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}