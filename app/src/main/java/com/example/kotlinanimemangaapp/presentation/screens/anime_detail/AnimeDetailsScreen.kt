package com.example.kotlinanimemangaapp.presentation.screens.anime_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.kotlinanimemangaapp.presentation.components.bar.AppBarBackButton
import com.example.kotlinanimemangaapp.presentation.components.buttons.AddIconButton
import com.example.kotlinanimemangaapp.presentation.components.buttons.FavoriteIconButton
import com.example.kotlinanimemangaapp.presentation.components.buttons.WatchIconButton
import com.example.kotlinanimemangaapp.presentation.components.dialog.CustomDialogListAnime


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimeDetailsScreen(
    navController: NavController,
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { AppBarBackButton(navController = navController) },
        bottomBar = {}
    ) {

        val listAnime = viewModel.stateListAnime.value
        val state = viewModel.state.value

        state.anime?.let { animeDetail ->
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Image en haut
                        Image(
                            contentDescription = "Anime Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.FillWidth,
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = animeDetail.anime.images?.jpg?.imageUrl.toString())
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        //placeholder(R.drawable.ic_placeholder)
                                    }).build()
                            ),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                // Titre du anime
                                Text(
                                    text = animeDetail.anime.title ?: "",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )
                                //Auteur du anime
                                /*Text(
                                    text = animeDetail.anime. .authors?.get(0)?.name ?: "",
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )*/
                                //Date de sortie
                                Text(
                                    text = animeDetail.anime.aired?.prop?.from?.year.toString() ?: "",
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )
                            }
                            FavoriteIconButton(
                                onClicked = {
                                    viewModel.addAnimeToFavorite(animeId = animeDetail.anime.malId)
                                },
                                isClicked = animeDetail.liked
                            )
                            WatchIconButton(
                                onClicked = {
                                    viewModel.addAnimeToWatch(animeId = animeDetail.anime.malId)
                                },
                                isClicked = animeDetail.watched
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clickable { }
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // Vous pouvez laisser cette partie vide, car le changement d'état sera déclenché par LaunchedEffect

                            AddIconButton(onClicked = {
                                viewModel.onPurchaseClick()
                                //viewModel.addAnimeToFavorite(animeId = animeDetail.anime.malId)
                            })
                        }

                        Spacer(modifier = Modifier.height(8.dp))


                        Text(
                            text = "Synopsis :",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // Description du anime
                        Text(
                            text = animeDetail.anime.synopsis ?: "",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Liste de cards avec du texte (à remplacer par les informations réelles du anime)
                        repeat(5) { index ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                            ) {
                                Text(
                                    text = "Élément $index",
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Personnages:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Personnage 1",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Personnage 2",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            if (viewModel.isDialogShown) {
                CustomDialogListAnime(
                    onDismiss = {
                        viewModel.onDismissDialog()
                    },
                    onConfirm = {
                    },
                    onItemClick = { listAnime, isInList ->
                        val idAnime: Int = animeDetail.anime.malId
                        val idList: String = listAnime.id
                        if (isInList) {
                            viewModel.addAnimeToAnimeList(idAnime,idList)
                        } else {
                            viewModel.removeAnimeFromAnimeList(idAnime,idList)
                        }
                    },
                    anime = animeDetail.anime,
                    animeLists = listAnime.list
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator()
        }
    }
}