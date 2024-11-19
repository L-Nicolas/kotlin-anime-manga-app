package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddIconButton(
    onClicked: () -> Unit = {},
    isClicked: Boolean = false
) {

    CustomIconButton(
        modifier = Modifier.padding(8.dp),
        icon = Icons.Default.Add,
        clickedIcon = Icons.Filled.Check,
        onClicked = onClicked,
        isClicked = isClicked
    )
}