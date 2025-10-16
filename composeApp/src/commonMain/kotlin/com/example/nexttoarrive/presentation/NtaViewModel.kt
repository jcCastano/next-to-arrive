package com.example.nexttoarrive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttoarrive.api.NextToArriveQuery
import com.example.nexttoarrive.data.NextToArriveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NtaViewModel(private val repo: NextToArriveRepository): ViewModel() {
    private val _state = MutableStateFlow<NtaUiState>(NtaUiState(stations = defaultStations()))
    val state: StateFlow<NtaUiState> = _state

    fun onEvent(event: NtaEvent) {
        when (event) {
            is NtaEvent.SelectOrigin -> reduce { it.copy(origin = event.station) }
            is NtaEvent.SelectDestination -> reduce { it.copy(destination = event.station) }
            is NtaEvent.Fetch -> fetch()
        }
    }

    private fun fetch() {
        reduce { it.copy(isLoading = true, trips = emptyList(), error = null) }

        val current = _state.value
        val origin = current.origin
        val destination = current.destination
        if (origin.isNullOrBlank() || destination.isNullOrBlank()) {
            reduce { it.copy(isLoading = false, error = "Origin and destination must be selected") }
            return
        } else if (origin == destination) {
            reduce { it.copy(isLoading = false, error = "Origin and destination must be different") }
            return
        }

        viewModelScope.launch {
            try {
                val result = repo.fetch(NextToArriveQuery(origin, destination))
                reduce { it.copy(isLoading = false, trips = result.trips) }
            } catch (e: Exception) {
                e.printStackTrace()
                reduce { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }

    private inline fun reduce(block: (NtaUiState) -> NtaUiState) {
        _state.value = block(_state.value)
    }

    private fun defaultStations() = listOf(
        "30th Street Station",
        "Suburban Station",
        "Temple University",
        "Jefferson Station",
        "Ardmore",
        "Overbrook"
    )

}