package com.example.app.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.data.dao.models.ScentStrength
import com.example.app.data.dao.models.Perfume
import com.example.app.data.dao.models.FragranceType
import com.example.app.ui.components.Button.CuteBloomButton
import com.example.app.ui.components.Button.CuteTypeButton
import com.example.app.ui.components.Card.CardWithBorder
import com.example.app.ui.components.FormsAndFields.CustomTextField

@Composable
fun CardList(
    modifier: Modifier = Modifier,
    list: List<Perfume>,
    onDeletePerfume: (Perfume) -> Unit,
    onEditPerfume: (Int) -> Unit,
    onToggleFavorite: (Perfume) -> Unit
) {
    val strengthTypes = listOf("All") + ScentStrength.entries.map { it.name }
    var selectedStrength by remember { mutableStateOf("All") }

    val fragranceTypes = listOf("All") + FragranceType.entries.map { it.name }
    var selectedType by remember { mutableStateOf("All") }

    var showDialog by remember { mutableStateOf(false) }
    var perfumeToDelete by remember { mutableStateOf<Perfume?>(null) }
    var searchText by remember { mutableStateOf("") }

    var filtersVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(8.dp)
    ) {
        CustomTextField(
            value = searchText,
            includeLabel = false,
            onValueChange = { searchText = it },
            labelText = "Search by name",
            placeholder = "name"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CuteBloomButton(
            text = if (filtersVisible) "Hide Filters ✿" else "Show Filters ✿",
            onClick = { filtersVisible = !filtersVisible },
            selected = filtersVisible
        )

        AnimatedVisibility(visible = filtersVisible) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(fragranceTypes.size) { index ->
                        val perfumeType = fragranceTypes[index]
                        val displayText = when (perfumeType) {
                            "ORIENTAL" -> "ORIENT."
                            "GOURMAND" -> "GOUR."
                            else -> perfumeType
                        }
                        CuteTypeButton(
                            text = displayText,
                            onClick = { selectedType = perfumeType},
                            selected = selectedType == perfumeType
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(strengthTypes.size) { index ->
                        val displayText = strengthTypes[index]
                        CuteBloomButton(
                            text = displayText,
                            onClick = { selectedStrength = displayText },
                            selected = selectedStrength == displayText
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                list.filter {
                    it.name.contains(searchText, ignoreCase = true) &&
                            (selectedStrength == "All" || it.scentStrength.name == selectedStrength) &&
                            (selectedType == "All" || it.type.name == selectedType)
                }
            ) { perfume ->
                CardWithBorder(
                    name = perfume.name,
                    botanicalName = perfume.officialName,
                    perfumeType = perfume.type.name,
                    bloomTime = perfume.scentStrength.name,
                    meaning = perfume.meaning,
                    isFavorite = perfume.favourite,
                    onDeletePerfume = {
                        perfumeToDelete = perfume
                        showDialog = false
                    },
                    onEditPerfume = { onEditPerfume(perfume.id) },
                    onToggleFavorite = { onToggleFavorite(perfume) }
                )
            }
        }

        DeleteBirdDialog(
            showDialog = showDialog,
            perfumeToDelete = perfumeToDelete,
            onDeleteConfirmed = { perfume ->

                    onDeletePerfume(perfume)
                    showDialog = true

            },
            onDismiss = { showDialog = false }
        )
    }
}
