
package com.example.app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.dao.PlantDao
import com.example.app.data.dao.models.BloomTime
import com.example.app.data.dao.models.Plant
import com.example.app.data.dao.models.PlantType
import com.example.app.ui.viewModel.events.PlantEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//
//@HiltViewModel
//class PlantViewModel @Inject constructor():ViewModel() {
//    private val _state = MutableStateFlow(
//        listOf(
//            Plant(
//                id = 1,
//                name = "Sunflower",
//                biologicalName = "Helianthus annuus",
//                type = PlantType.ANNUALS,
//                bloomTime = BloomTime.SUMMER,
//                meaning = "Adoration"
//            ),
//            Plant(
//                id = 2,
//                name = "Tulip",
//                biologicalName = "Tulipa",
//                type = PlantType.PERENNIALS,
//                bloomTime = BloomTime.SPRING,
//                meaning = "Perfect love"
//            ),
//            Plant(
//                id = 3,
//                name = "Rose",
//                biologicalName = "Rosa",
//                type = PlantType.PERENNIALS,
//                bloomTime = BloomTime.SUMMER,
//                meaning = "Love and passion"
//            ),
//            Plant(
//                id = 4,
//                name = "Cabbage",
//                biologicalName = "Brassica oleracea",
//                type = PlantType.BIENNIALS,
//                bloomTime = BloomTime.SPRING,
//                meaning = "Cultivation"
//            )
//        )
//    )
//    val state = _state.asStateFlow()
//
//    fun getPlantById(id: Int): Plant? {
//        return _state.value.firstOrNull { it.id == id }
//    }
//
//    fun updatePlant(editedPlant: Plant) {
//        _state.update { currentList ->
//            currentList.map { plant ->
//                if (plant.id == editedPlant.id) editedPlant else plant
//            }
//        }
//
//    }
//
//    fun addPlant(addedPlant: Plant) {
//
//        val nextId = (_state.value.maxOfOrNull { it.id } ?: 0) + 1
//
//
//        val plantWithId = addedPlant.copy(id = nextId)
//
//
//        _state.update { currentList ->
//            currentList + plantWithId
//        }
//    }
//
//
//    fun deletePlant(id: Int) {
//        _state.update { currentList ->
//            currentList.filter { it.id != id }
//        }
//    }
//
//}


@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantDao: PlantDao
):ViewModel() {
    private val _state = MutableStateFlow<List<Plant>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            plantDao.getPlants()
                .collect { plants ->
                    _state.value = plants
                }
        }
    }

    fun onEvent(event: PlantEvent) {
        when (event) {
            is PlantEvent.AddPlant -> addPlant(event.plant)
            is PlantEvent.UpdatePlant -> updatePlant(event.plant)
            is PlantEvent.DeletePlant -> deletePlant(event.plant)
            is PlantEvent.ToggleFavorite -> toggleFavorite(event.plant)

        }
    }

    private fun toggleFavorite(plant: Plant) {
        viewModelScope.launch {
            val updatedPlant = plant.copy(favourite = !plant.favourite)
            plantDao.addPlant(updatedPlant)
        }
    }

    private fun addPlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.addPlant(plant)
        }
    }

    private fun updatePlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.addPlant(plant)
        }
    }

    private fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.deletePlant(plant)

        }
    }

    fun getPlantById(id: Int): Plant? {
        return _state.value.find { it.id == id }
    }


}