package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TrashIconButton(
    onClicked: () -> Unit = {},
    isClicked: Boolean = false
) {
    CustomIconButton(
        modifier = Modifier.padding(8.dp),
        icon = Icons.Default.Edit,
        clickedIcon = Icons.Filled.Edit,
        color = Color(0xffE91E63),
        onClicked = onClicked,
        isClicked = isClicked
    )
}