package com.example.kotlinanimemangaapp.presentation.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.kotlinanimemangaapp.domain.model.Anime

@Composable
fun CustomItem(
    anime: Anime,
    onItemClick: (Anime) -> Unit
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemClick(anime) }
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        coilImageWithShadow(url = anime.images?.jpg?.imageUrl.toString())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(anime) }
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = anime.title ?: "",
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = anime.aired?.prop?.from?.year.toString() ?: "",
                    color = Color.Black,
                    style = MaterialTheme.typography.subtitle1,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "·",
                    color = Color.Black,
                    style = MaterialTheme.typography.subtitle1,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Row {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = "${anime.score.toString()}" ?: "",
                        color = Color.Black,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/*@Composable
fun coilImage(url: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder)
            }).build()
        ),
        contentDescription = "Anime Image",
        contentScale = ContentScale.Crop
    )
}
*/
@Composable
fun coilImageWithShadow(url: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .graphicsLayer(shape = RoundedCornerShape(8.dp))
            .shadow(4.dp, RoundedCornerShape(8.dp), clip = true),

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

@Composable
fun coilImage(url: String) {
    Image(
        modifier = Modifier
            .width(120.dp)
            .height(250.dp)
            .graphicsLayer(shape = RoundedCornerShape(8.dp))
            .shadow(4.dp, RoundedCornerShape(8.dp), clip = true),

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