package com.example.app.ui.components.Button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.Purple
import com.example.app.ui.theme.Purple40
import com.example.app.ui.theme.Purple80
import com.example.app.ui.theme.PurpleGrey40
import com.example.app.ui.theme.PurpleGrey80

@Composable
fun CuteTypeButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Purple40 else PurpleGrey80, label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) Color.White else PurpleGrey40, label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable { onClick() }
            .background(color = backgroundColor, shape = RoundedCornerShape(60.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)

    ) {
        Text(text = text, color = textColor)
    }
}


