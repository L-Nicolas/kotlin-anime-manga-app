package com.example.kotlinanimemangaapp.presentation.components.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.presentation.ui.theme.Shapes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialogListAnime(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onItemClick: (listAnime: ListAnime, isInList: Boolean) -> Unit,
    anime: Anime,
    animeLists: List<ListAnime>
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = Shapes.small,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDismiss()
                        }
                ) {
                    Text(
                        text = "Enregistrer dans...",
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }

                // Afficher la liste des listes de animes avec des cases à cocher à côté de chaque liste
                AnimeListRow(
                    anime,
                    animeLists,
                    onItemClick
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onConfirm()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Ok",
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeListRow(
    anime: Anime,
    animeList: List<ListAnime>,
    onItemClick: (listAnime: ListAnime, isInList: Boolean) -> Unit
) {
    Column {
        // Afficher la liste des animes avec des cases à cocher à côté de chaque anime pour indiquer l'appartenance
        animeList.forEach { list ->
            list.anime?.let {
                AnimeListItemRow(
                    list,
                    it.any{it == anime.malId},
                    onItemClick
                )
            }
        }
    }
}

@Composable
fun AnimeListItemRow(
    anime: ListAnime,
    isPartOfList: Boolean,
    onItemClick: (listAnime: ListAnime, isInList: Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(anime.name)
        Checkbox(
            checked = isPartOfList,
            onCheckedChange = {
                onItemClick(anime, it)
            }
        )
    }
}
