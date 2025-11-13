package com.example.app.ui.components.FormsAndFields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.theme.ryeFontFamily


@Composable
fun TextArea(value:String, onValueChange:(String)->Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MediumText20(text="MEANING")
        Spacer(Modifier.width(30.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    "Enter your text here",
                    style = TextStyle(fontFamily = ryeFontFamily,textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier.fillMaxWidth().height(294.dp),
            textStyle = TextStyle(
                fontFamily = ryeFontFamily,
                textAlign = TextAlign.Center
            ),
            singleLine = false,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}