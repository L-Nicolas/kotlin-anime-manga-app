package com.example.kotlinanimemangaapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardDemo(
    text: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White)
            .graphicsLayer(shape = RoundedCornerShape(4.dp)),
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = text
            )
        }
    }
}

@Preview
@Composable
fun CardDemoPreview() {
    CardDemo(
        text = "Hello"
    )
}