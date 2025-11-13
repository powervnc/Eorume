package com.example.app.ui.components.Text.MediumText

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.app.ui.components.Text.CustomText

@Composable
fun MediumText15(
    text: String,
    colorText: Color = Color.Black,

    ){
    CustomText(text=text,
        colorText=colorText,
        fontSize = 15.sp,
    )
}