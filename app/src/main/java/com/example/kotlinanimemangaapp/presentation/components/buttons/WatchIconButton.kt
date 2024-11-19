package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WatchIconButton(
    onClicked: () -> Unit = {},
    isClicked: Boolean = false
) {
    CustomIconButton(
        modifier = Modifier.padding(8.dp),
        icon = Icons.Default.CheckCircle,
        clickedIcon = Icons.Filled.Settings,
        color = Color(0xFF841EE9),
        onClicked = onClicked,
        isClicked = isClicked
    )
}