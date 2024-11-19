package com.example.kotlinanimemangaapp.presentation.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.presentation.ui.theme.Shapes


@Composable
fun AnimeItemSmall(
    anime: Anime,
    onItemClick: (Anime) -> Unit
) {
    println("AnimeItemSmall: ${anime.images?.jpg?.largeImageUrl.toString()}")
    Surface(
        shape = Shapes.medium,
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .clickable { onItemClick(anime) }
    ) {
        coilImageSmall(url = anime.images?.jpg?.largeImageUrl.toString())
    }
}

@Composable
fun coilImageSmall(url: String) {
    Image(
        /*modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),*/

        modifier = Modifier
            .width(120.dp)
            .height(250.dp),

        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                //placeholder(R.drawable.ic_placeholder)
            }).build()
        ),
        contentDescription = "Anime Image",
        contentScale = ContentScale.Crop
    )
}
