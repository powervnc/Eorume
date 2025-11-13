package com.example.app.ui.components.Text

import androidx.compose.ui.text.font.FontFamily



import androidx.compose.material3.Text

import androidx.compose.runtime.Composable;
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.example.app.ui.theme.ryeFontFamily

@Composable
fun CustomText(
    text: String, colorText: Color, fontSize: TextUnit,
    fontFamily: FontFamily? = null,
    maxLines: Int = 5,
) {
    val fontToUse = fontFamily ?: ryeFontFamily
    Text(

        text=text,
        style = TextStyle(
            color=colorText,
            fontSize=fontSize,
            fontFamily = fontToUse
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,


    )
}
