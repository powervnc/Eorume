package com.example.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.app.ui.components.Text.MediumText.MediumText15
import com.example.app.ui.components.Text.SmallText


@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    roundedValue : Int = 10
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor),
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Black),
        enabled = enabled,
        shape = RoundedCornerShape(roundedValue.dp),

    ) {
        MediumText15(text = text, colorText = textColor)
    }

}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview(){
    CustomButton("hello", Color.Cyan, {}, textColor = Color.Black)

}