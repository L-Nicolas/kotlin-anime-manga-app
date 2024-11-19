package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIconButton(
    onClicked: () -> Unit = {},
    isClicked: Boolean = false
) {
    CustomIconButton(
        modifier = Modifier.padding(8.dp),
        icon = Icons.Default.FavoriteBorder,
        clickedIcon = Icons.Filled.Favorite,
        color = Color(0xffE91E63),
        onClicked = onClicked,
        isClicked = isClicked
    )
}