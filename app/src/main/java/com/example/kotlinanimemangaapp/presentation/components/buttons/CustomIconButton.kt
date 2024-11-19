package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF202122),
    icon: ImageVector,
    clickedIcon: ImageVector,
    onClicked: () -> Unit,
    isClicked: Boolean = false
) {
    var clicked by remember { mutableStateOf(isClicked) }

    IconToggleButton(
        checked = clicked,
        onCheckedChange = {
            clicked = !clicked
            onClicked()
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (clicked) {
                clickedIcon
            } else {
                icon
            },
            contentDescription = null
        )
    }
}