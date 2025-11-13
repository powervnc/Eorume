package com.example.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.components.Text.MediumText.MediumText24


@Composable
fun Header(title:String, description:String) {
    val background = Color.White
    val textColor = Color.Black
    Column(horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxWidth().background(background).padding(top = 8.dp,start=18.dp)) {
        MediumText24(text = title, colorText = textColor)
        MediumText20(text = description, colorText = textColor)
        Spacer(modifier = Modifier.height(5.dp))
    }

}
@Preview
@Composable
fun HeaderPreview(){
    Header("Your flowers", "Admire your flowers")
}