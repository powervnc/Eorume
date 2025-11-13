package com.example.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app.data.dao.models.Perfume
import com.example.app.ui.components.CardList


@Composable
fun ListScreen(
    state: List<Perfume>,
    onDeletePerfume: (Perfume) -> Unit,
    //onClickPlusButton: () -> Unit,
    onEditPerfume: (Int) -> Unit,
    onToggleFavorite: (Perfume) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CardList(list = state,  onDeletePerfume=onDeletePerfume, onEditPerfume=onEditPerfume, onToggleFavorite = onToggleFavorite)
        }

    }

}