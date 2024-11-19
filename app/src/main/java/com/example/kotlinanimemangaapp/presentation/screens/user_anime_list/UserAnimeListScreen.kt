package com.example.kotlinanimemangaapp.presentation.screens.user_liste_animes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlinanimemangaapp.presentation.components.CardDemo
import com.example.kotlinanimemangaapp.presentation.components.CustomDialogListAnimeForm
import com.example.kotlinanimemangaapp.presentation.components.bar.BottomBar
import com.example.kotlinanimemangaapp.presentation.screens.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserAnimeListScreen(
    navController: NavController,
    viewModel: UserAnimeListViewModel = hiltViewModel()
) {

    val stateListAnime = viewModel.stateListAnime.value
    val stateAnimeWatched = viewModel.stateAnimeWatched.value
    val stateFavorite = viewModel.stateListFavorite.value

    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Scaffold(
        topBar = { AddButtonAppBar { viewModel.onPurchaseClick() } },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = viewModel::loadListAnimeOfUser,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .background(Color.White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {
                    val watchedAnime = stateAnimeWatched.animes
                    LazyColumn(
                        contentPadding = PaddingValues(all = 12.dp)
                    ) {
                        items(watchedAnime) { listAnime ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                                    .padding(12.dp)
                                    .clickable {
                                        navController.navigate(Screen.UserListDetail.passId(listAnime.id))
                                    },
                                text = listAnime.toString()
                            )

                        }
                    }
                    if (stateFavorite.isLoading) {
                        CircularProgressIndicator()
                    }
                    if (stateFavorite.error.isNotBlank()) {
                        CardDemo(stateFavorite.error)
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {
                    val favoriteAnime = stateFavorite.animes
                    LazyColumn(
                        contentPadding = PaddingValues(all = 12.dp)
                    ) {
                        items(favoriteAnime) { listAnime ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                                    .padding(12.dp)
                                    .clickable {
                                        navController.navigate(Screen.UserListDetail.passId(listAnime.id))
                                    },
                                text = listAnime.toString()
                            )

                        }
                    }
                    if (stateFavorite.isLoading) {
                        CircularProgressIndicator()
                    }
                    if (stateFavorite.error.isNotBlank()) {
                        CardDemo(stateFavorite.error)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    val fewAnime = stateListAnime.animes
                    LazyColumn(
                        contentPadding = PaddingValues(all = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(fewAnime) { listAnime ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                                    .padding(12.dp)
                                    .clickable {
                                        navController.navigate(Screen.UserListDetail.passId(listAnime.id))
                                    },
                                text = listAnime.toString(),
                            )

                        }
                    }
                    if (stateListAnime.isLoading) {
                        CircularProgressIndicator()
                    }
                    if (stateListAnime.error.isNotBlank()) {
                        //a tester
                        //CardDemo(stateListAnime.error)
                        Toast.makeText(
                            LocalContext.current,
                            stateListAnime.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        if(viewModel.isDialogShown){
            CustomDialogListAnimeForm(
                onDismiss = {
                    viewModel.onDismissDialog()
                },
                onConfirm = {
                    viewModel.onAddListClick("")
                }
            )
        }
    }
}
@Composable
fun AddButtonAppBar(onClick: () -> Unit = {}) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Mes animés",
                    textAlign = TextAlign.Center
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}

