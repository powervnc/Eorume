package com.example.app.ui.screens

import androidx.compose.runtime.Composable
import com.example.app.data.dao.models.Perfume


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.app.ui.components.FormsAndFields.CustomForm

@Composable
fun AddEditPerfumeScreen(
    text: String,
    subText: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onSubmit: (Perfume) -> Unit,
    existingPerfume: Perfume? = null
) {


    Column(modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally) {
        CustomForm(onSubmit = onSubmit, existingPerfume = existingPerfume, buttonText = buttonText)
    }
}


