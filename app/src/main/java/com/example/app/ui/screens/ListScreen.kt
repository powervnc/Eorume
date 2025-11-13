package com.example.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.data.dao.models.Plant
import com.example.app.ui.components.CardList
import com.example.app.ui.components.FloatingPlusButton
import com.example.app.ui.components.Header


@Composable
fun ListScreen(
    state: List<Plant>,
    onDeletePlant: (Plant) -> Unit,
    //onClickPlusButton: () -> Unit,
    onEditPlant: (Int) -> Unit,
    onToggleFavorite: (Plant) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CardList(list = state,  onDeletePlant=onDeletePlant, onEditPlant=onEditPlant, onToggleFavorite = onToggleFavorite)
        }

    }

}