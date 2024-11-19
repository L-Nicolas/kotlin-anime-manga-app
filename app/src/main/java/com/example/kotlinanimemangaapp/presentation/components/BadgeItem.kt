package com.example.kotlinanimemangaapp.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotlinanimemangaapp.domain.model.Genre
import com.example.kotlinanimemangaapp.presentation.screens.anime_list.AnimeListViewModel
import com.example.kotlinanimemangaapp.presentation.ui.theme.Shapes

@Composable
fun BadgeItem(
    modifier: Modifier = Modifier,
    shape: Shape = Shapes.extraLarge,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    genre: Genre,
    viewModel: AnimeListViewModel,
) {
    // Utilisez viewModel.selectedGenres pour obtenir l'état de sélection du ViewModel
    val selectedGenres by viewModel.selectedGenres

    Surface(
        modifier = modifier.clickable {
            // Appeler toggleSelected() du ViewModel pour mettre à jour l'état de sélection
            viewModel.toggleCategorySelection(genre)
        },
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = if (selectedGenres.contains(genre)) Color.Blue else backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = genre.name ?: "",
                color = if (selectedGenres.contains(genre)) Color.White else Color.Black,
            )
        }
    }
}
