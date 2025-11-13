package com.example.app.ui.components.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun SmallText(text:String, colorText:Color= Color.Black, maxLines: Int ){
    CustomText(
        text=text,
        colorText=colorText,
        fontSize = 13.sp,
        maxLines = maxLines,
    )
}

