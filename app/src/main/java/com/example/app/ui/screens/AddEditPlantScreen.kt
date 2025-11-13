package com.example.app.ui.screens

import androidx.compose.runtime.Composable
import com.example.app.data.dao.models.Plant


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.ui.components.FormsAndFields.CustomForm

@Composable
fun AddEditPlantScreen(
    text: String,
    subText: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onSubmit: (Plant) -> Unit,
    existingPlant: Plant? = null
) {


    Column(modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally) {
        CustomForm(onSubmit = onSubmit, existingPlant = existingPlant, buttonText = buttonText)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddEditPlantScreen(){
    AddEditPlantScreen(text="Edit Begonia", subText = "edit your favourite flowrs", buttonText = "text", onSubmit = {})
}
