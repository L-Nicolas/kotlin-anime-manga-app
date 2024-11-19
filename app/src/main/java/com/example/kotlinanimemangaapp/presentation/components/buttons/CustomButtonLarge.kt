package com.example.kotlinanimemangaapp.presentation.components.buttons

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinanimemangaapp.presentation.ui.theme.Shapes

@Composable
fun CustomButtonLarge(
    modifier: Modifier = Modifier,
    text: String = "Bouton",
    shape: Shape = Shapes.small,
    borderColor: Color = Color.Blue,
    backgroundColor: Color = Color.Blue,
    onClicked: () -> Unit? = { null }
) {
    var clicked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.clickable { clicked = !clicked }.fillMaxWidth(),
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
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
                color = Color.White,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold,
                text = text
            )
            if (clicked) onClicked()
        }
    }
}

@Composable
@Preview
private fun CustomButtonLargePreview() {
    CustomButtonLarge(text = "Créer une playlist")
}