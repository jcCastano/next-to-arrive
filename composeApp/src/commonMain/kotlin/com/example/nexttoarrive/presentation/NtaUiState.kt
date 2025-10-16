package com.example.nexttoarrive.presentation

import com.example.nexttoarrive.model.NextToArriveResponse
import com.example.nexttoarrive.model.NtaTrip

data class NtaUiState(
    val origin: String? = null,
    val destination: String? = null,
    val stations: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val trips: List<NtaTrip> = emptyList(),
    val error: String? = null
)

sealed interface NtaEvent {
    data class SelectOrigin(val station: String): NtaEvent
    data class SelectDestination(val station: String): NtaEvent
    data object Fetch: NtaEvent

}
