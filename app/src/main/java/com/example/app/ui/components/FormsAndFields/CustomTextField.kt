package com.example.app.ui.components.FormsAndFields


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.components.Text.SmallText
import com.example.app.ui.theme.ryeFontFamily


@Preview
@Composable
fun CustomTextField(
    includeLabel: Boolean = true,
    placeholder: String="placeholder",
    labelText: String="label",
    value: String="value",
    onValueChange: (String) -> Unit = { message:String ->
    Log.d("MyTag", message)
}) {
    val inputTextColor =  MaterialTheme.colorScheme.onSurfaceVariant
    val containerColor =  MaterialTheme.colorScheme.surfaceVariant


    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
        if (includeLabel) {
            MediumText20(
                text = labelText,
                colorText = MaterialTheme.colorScheme.onBackground
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                SmallText(text = placeholder, colorText = Color(0xFF9E88CC), maxLines = 5)
            },
            textStyle = TextStyle(
                fontFamily = ryeFontFamily,
                fontSize = 13.sp,
                color = Color(0xFF3D0075)
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFEDE0FF),
                unfocusedContainerColor = Color(0xFFEDE0FF),
                focusedTextColor = Color(0xFF3D0075),
                unfocusedTextColor = Color(0xFF3D0075),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )

        )
    }

}


