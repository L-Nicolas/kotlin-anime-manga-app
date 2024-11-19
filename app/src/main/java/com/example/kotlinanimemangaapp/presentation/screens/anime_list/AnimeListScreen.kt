package com.example.kotlinanimemangaapp.presentation.screens.anime_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlinanimemangaapp.presentation.components.BadgeItem
import com.example.kotlinanimemangaapp.presentation.components.items.CustomItem
import com.example.kotlinanimemangaapp.presentation.components.items.AnimeItemSmall
import com.example.kotlinanimemangaapp.presentation.components.bar.BottomBar
import com.example.kotlinanimemangaapp.presentation.components.bar.MainAppBar
import com.example.kotlinanimemangaapp.presentation.components.bar.SearchWidgetState
import com.example.kotlinanimemangaapp.presentation.screens.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimeListScreen(
    navController: NavController,
    viewModel: AnimeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val stateListGenres = viewModel.stateListGenres.value

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEventChanged(AnimeListScreenEvent.OnRefresh) },
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(all = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(stateListGenres.genres) { genre ->
                            Spacer(modifier = Modifier.padding(5.dp))
                            BadgeItem(viewModel = viewModel, genre = genre)
                        }
                    }
                }

                itemsIndexed(state.animes.chunked(3)) { index, rowAnime ->
                    Box(Modifier.fillMaxWidth()) {
                        LazyRow(
                            contentPadding = PaddingValues(all = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                        ) {
                            if(index < 1){
                                items(rowAnime) { anime ->
                                    CustomItem(
                                        anime = anime,
                                        onItemClick = {
                                            navController.navigate(Screen.Details.passId(anime.malId))
                                        }
                                    )
                                }
                            } else {
                                items(rowAnime) { anime ->
                                    AnimeItemSmall(
                                        anime = anime,
                                        onItemClick = {
                                            navController.navigate(Screen.Details.passId(anime.malId))
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }

    }
}

