package com.example.app.ui.viewModel.events

import com.example.app.data.dao.models.Perfume

sealed interface PerfumeEvent {
    data class AddPerfume(val perfume: Perfume) : PerfumeEvent
    data class UpdatePerfume(val perfume: Perfume) : PerfumeEvent
    data class DeletePerfume(val perfume: Perfume) : PerfumeEvent
    data class ToggleFavorite(val perfume: Perfume) : PerfumeEvent
}
