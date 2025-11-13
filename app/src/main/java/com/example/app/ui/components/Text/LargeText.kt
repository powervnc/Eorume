package com.example.app.ui.components.Text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LargeText(text:String, colorText:Color= Color.Black ){
    Box(
        modifier = Modifier.border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(20.dp)).padding(10.dp, vertical = 8.dp)
    ) {
        CustomText(
            text = text,
            colorText = colorText,
            fontSize = 30.sp,

            )
    }
}