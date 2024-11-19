package com.example.kotlinanimemangaapp.presentation.screens.user_list_animes_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlinanimemangaapp.presentation.components.bar.AppBarBackButton
import com.example.kotlinanimemangaapp.presentation.components.items.AnimeItemInList
import com.example.kotlinanimemangaapp.presentation.screens.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserAnimeListDetailScreen(
    navController: NavController,
    viewModel: UserAnimeListDetailViewModel = hiltViewModel()
) {
    val listTitle = "Ma Liste de Anime"

    val state = viewModel.stateListAnime.value
    val animeListDetail = state.animeListDetail

    Scaffold(
        topBar = { AppBarBackButton(navController = navController) },
        bottomBar = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Image en haut
            /*Image(
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = "List Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp).clickable(onClick = {

                    }),
                contentScale = ContentScale.FillWidth
            )*/

            Button(onClick = {
                if (animeListDetail != null) {
                    viewModel.deleteList(idList = animeListDetail.id)
                }
            }) {
                Text(text = "Supprimer la liste")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Titre de la liste
            Text(
                text = listTitle,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nom de l'utilisateur
            if (animeListDetail != null) {
                Text(
                    text = "By ${animeListDetail.id_user}",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre d'éléments dans la liste avec le statut
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (animeListDetail != null) {
                    Text(
                        text = "${animeListDetail.animes.size} éléments}",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(8f)
                    )
                }
                if (animeListDetail != null) {
                    Text(
                        text = if (animeListDetail.shared) "Public" else "Privé",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .weight(2f)
                            .clickable(
                                onClick = {
                                    viewModel.changeSharedList(listId = animeListDetail.id)
                                }
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(all = 12.dp)
                ) {
                    if (animeListDetail != null) {
                        items(animeListDetail.animes) { anime ->
                            AnimeItemInList(
                                anime = anime,
                                onItemClick = {
                                    navController.navigate(Screen.Details.passId(anime.malId))
                                },
                                onEditClick = {
                                    viewModel.deleteAnimeFromList(
                                        idList = animeListDetail.id,
                                        idAnime = anime.malId
                                    )
                                }
                            )
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
}
