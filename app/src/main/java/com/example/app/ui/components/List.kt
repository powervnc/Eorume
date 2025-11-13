package com.example.app.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.app.data.dao.models.BloomTime
import com.example.app.data.dao.models.Plant
import com.example.app.data.dao.models.PlantType
import com.example.app.ui.components.Button.CuteBloomButton
import com.example.app.ui.components.Button.CuteTypeButton
import com.example.app.ui.components.Card.CardWithBorder
import com.example.app.ui.components.FormsAndFields.CustomTextField

@Composable
fun CardList(
    modifier: Modifier = Modifier,
    list: List<Plant>,
    onDeletePlant: (Plant) -> Unit,
    onEditPlant: (Int) -> Unit,
    onToggleFavorite: (Plant) -> Unit
) {
    val bloomTimes = listOf("All") + BloomTime.entries.map { it.name }
    var selectedBloom by remember { mutableStateOf("All") }

    val plantTypes = listOf("All") + PlantType.entries.map { it.name }
    var selectedType by remember { mutableStateOf("All") }

    var showDialog by remember { mutableStateOf(false) }
    var plantToDelete by remember { mutableStateOf<Plant?>(null) }
    var searchText by remember { mutableStateOf("") }

    var filtersVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(8.dp)
    ) {
        CustomTextField(
            value = searchText,
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
                    items(plantTypes.size) { index ->
                        val plantType = plantTypes[index]
                        val displayText = when (plantType) {
                            "PERENNIALS" -> "Per."
                            "ANNUALS" -> "Ann."
                            "BIENNIALS" -> "Bien."
                            else -> plantType
                        }
                        CuteTypeButton(
                            text = displayText,
                            onClick = { selectedType = plantType},
                            selected = selectedType == plantType
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(bloomTimes.size) { index ->
                        val displayText = bloomTimes[index]
                        CuteBloomButton(
                            text = displayText,
                            onClick = { selectedBloom = displayText },
                            selected = selectedBloom == displayText
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
                            (selectedBloom == "All" || it.bloomTime.name == selectedBloom) &&
                            (selectedType == "All" || it.type.name == selectedType)
                }
            ) { plant ->
                CardWithBorder(
                    name = plant.name,
                    botanicalName = plant.biologicalName,
                    plantType = plant.type.name,
                    bloomTime = plant.bloomTime.name,
                    meaning = plant.meaning,
                    isFavorite = plant.favourite,
                    onDeletePlant = {
                        Log.d("Test", "Delete clicked for ${plant.name}")
                        plantToDelete = plant
                        showDialog = true
                    },
                    onEditPlant = { onEditPlant(plant.id) },
                    onToggleFavorite = { onToggleFavorite(plant) }
                )
            }
        }

        DeleteBirdDialog(
            showDialog = showDialog,
            plantToDelete = plantToDelete,
            onDeleteConfirmed = { plant ->
                onDeletePlant(plant)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}
