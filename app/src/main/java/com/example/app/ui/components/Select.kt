package com.example.app.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.app.ui.components.Text.SmallText


@Composable
fun Select(
    selectedValue: String,
    onValueChange: (String) -> Unit={ message:String ->
        Log.d("MyTag", message)},
    modifier: Modifier = Modifier,
    values: List<String>,
    selectLabel:String="BLOOM TIME"
) {
    var expanded by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue(selectedValue)) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            label = { SmallText(text = selectLabel, colorText = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1) },

            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            values.forEach { value ->
                DropdownMenuItem(
                    text = { SmallText(value, maxLines = 1) },
                    onClick = {
                        textState.value = TextFieldValue(value)
                        onValueChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}

