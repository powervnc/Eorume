package com.example.app.ui.viewModel.events

import com.example.app.data.dao.models.Plant

sealed interface PlantEvent {
    data class AddPlant(val plant: Plant) : PlantEvent
    data class UpdatePlant(val plant: Plant) : PlantEvent
    data class DeletePlant(val plant: Plant) : PlantEvent
    data class ToggleFavorite(val plant: Plant) : PlantEvent

//    data class ShowDeleteDialog(val id: Int) : PlantEvent
//    object CloseDeleteDialog : PlantEvent
//
//    data class SortContents(val sortType: SortType): PlantEvent
}

enum class SortType {
    NAME,
    BOTANICAL_NAME
}
