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
    placeholder: String="placeholder",
    labelText: String="label",
    value: String="value",
    onValueChange: (String) -> Unit = { message:String ->
    Log.d("MyTag", message)
}) {
    val inputTextColor =  MaterialTheme.colorScheme.onSurfaceVariant
    val containerColor =  MaterialTheme.colorScheme.surfaceVariant


    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {

//        MediumText20(
//            text = labelText,
//            colorText = MaterialTheme.colorScheme.onBackground
//        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                SmallText(text = placeholder, colorText = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 5)
            },
            textStyle = TextStyle(
                fontFamily = ryeFontFamily,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedTextColor = inputTextColor,
                unfocusedTextColor = inputTextColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )

        )
    }

}


