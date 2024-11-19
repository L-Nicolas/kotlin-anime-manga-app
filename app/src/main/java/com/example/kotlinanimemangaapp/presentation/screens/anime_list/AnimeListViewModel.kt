package com.example.kotlinanimemangaapp.presentation.screens.anime_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.AnimeInteractor
import com.example.kotlinanimemangaapp.domain.interactor.get_genres.GetGenresUC
import com.example.kotlinanimemangaapp.domain.interactor.get_anime.GetAnimeUC
import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.presentation.components.bar.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val interactor: AnimeInteractor,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    private val _state = mutableStateOf(AnimeListState())
    val state: State<AnimeListState> = _state

    private val _stateListGenres = mutableStateOf(ListGenresState())
    val stateListGenres: State<ListGenresState> = _stateListGenres

    private val _selectedGenres = mutableStateOf<List<Genre>>(emptyList())
    val selectedGenres: State<List<Genre>> get() = _selectedGenres

    init {
        _selectedGenres.value = emptyList()
        loadAnime()
    }

    fun onEventChanged(event: AnimeListScreenEvent){
        when(event){
            AnimeListScreenEvent.OnRefresh -> loadAnime()
        }
    }

    fun loadAnime() {
        getAnime()
        getGenres()
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
        getAnime(_searchTextState.value)
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        getAnime(_searchTextState.value)
    }

    fun toggleCategorySelection(genre: Genre) {
        if (selectedGenres.value.contains(genre)) {
            _selectedGenres.value = emptyList()
        } else {
            _selectedGenres.value = listOf(genre)
        }

        if(selectedGenres.value.isEmpty()) {
            getAnime()
        } else {
            getAnime(listIdGenre = _selectedGenres.value.map { it.malId })
        }
    }

    private fun getGenres() {
        interactor.getGenresUC().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _stateListGenres.value = ListGenresState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateListGenres.value = ListGenresState(genres = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateListGenres.value = ListGenresState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAnime(searchText: String = "", listIdGenre: List<Int> = emptyList()) {
        interactor.getAnimeUC(searchText,listIdGenre).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = AnimeListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = AnimeListState(animes = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AnimeListState(error = resource.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}