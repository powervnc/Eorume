
package com.example.app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.dao.PerfumeDao
import com.example.app.data.dao.models.Perfume
import com.example.app.ui.viewModel.events.PerfumeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class PefumeViewModel @Inject constructor(
    private val perfumeDao: PerfumeDao
):ViewModel() {
    private val _state = MutableStateFlow<List<Perfume>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            perfumeDao.getPerfumes()
                .collect { perfumes ->
                    _state.value = perfumes
                }
        }
    }

    fun onEvent(event: PerfumeEvent) {
        when (event) {
            is PerfumeEvent.AddPerfume -> addPerfume(event.perfume)
            is PerfumeEvent.UpdatePerfume -> updatePerfume(event.perfume)
            is PerfumeEvent.DeletePerfume -> deletePerfume(event.perfume)
            is PerfumeEvent.ToggleFavorite -> toggleFavorite(event.perfume)

        }
    }

    private fun toggleFavorite(perfume: Perfume) {
        viewModelScope.launch {
            val updatedPerfume = perfume.copy(favourite = !perfume.favourite)
            perfumeDao.addPerfume(updatedPerfume)
        }
    }

    private fun addPerfume(perfume: Perfume) {
        viewModelScope.launch {
            perfumeDao.addPerfume(perfume)
        }
    }

    private fun updatePerfume(perfume: Perfume) {
        viewModelScope.launch {
            perfumeDao.addPerfume(perfume)
        }
    }

    private fun deletePerfume(perfume: Perfume) {
        viewModelScope.launch {
            perfumeDao.deletePerfume(perfume)

        }
    }

    fun getPerfumeById(id: Int): Perfume? {
        return _state.value.find { it.id == id }
    }


}