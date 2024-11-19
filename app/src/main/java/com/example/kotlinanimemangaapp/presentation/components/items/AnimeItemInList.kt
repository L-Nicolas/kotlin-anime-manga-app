package com.example.kotlinanimemangaapp.presentation.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.presentation.components.buttons.TrashIconButton

@Composable
fun AnimeItemInList(
    anime: Anime,
    onItemClick: (Anime) -> Unit,
    onEditClick: (Anime) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(Color.LightGray, MaterialTheme.shapes.medium)
            .height(150.dp)
            .widthIn(max = 300.dp)
            .clickable { onItemClick(anime) }
            .padding(4.dp)
    ) {
        //coilImageSmall(url = anime.images?.jpg?.imageUrl.toString())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { onItemClick(anime) }
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = anime.title ?: "",
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                maxLines = 2, // Limiting the text to 2 lines
                overflow = TextOverflow.Ellipsis // Truncating the text with ellipsis if it exceeds the 2 lines
            )
            TrashIconButton(onClicked = { onEditClick(anime) })
        }

    }
}