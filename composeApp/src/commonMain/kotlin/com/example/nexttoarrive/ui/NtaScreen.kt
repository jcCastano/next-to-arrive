package com.example.nexttoarrive.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexttoarrive.presentation.NtaEvent
import com.example.nexttoarrive.presentation.NtaViewModel

@Composable
fun NtaScreen(viewModel: NtaViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Next to Arrive", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        StationDropdown(
            label = "Origin Station",
            options = state.stations,
            selected = state.origin,
            onSelected = { viewModel.onEvent(NtaEvent.SelectOrigin(it)) }
        )

        Spacer(Modifier.height(16.dp))

        StationDropdown(
            label = "Destination Station",
            options = state.stations,
            selected = state.destination,
            onSelected = { viewModel.onEvent(NtaEvent.SelectDestination(it)) }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onEvent(NtaEvent.Fetch) },
            enabled = !state.isLoading
        ) {
            if (state.isLoading) CircularProgressIndicator(Modifier.size(18.dp))
            else Text("Fetch Trains")
        }

        Spacer(Modifier.height(16.dp))

        state.error?.let { Text("Error: $it", color = MaterialTheme.colorScheme.error) }

        if (state.trips.isNotEmpty()) {
            LazyColumn {
                items(state.trips) { trip ->
                    Card(Modifier.fillMaxWidth().padding(4.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            Text("${trip.origLine ?: "Unknown Line"} (#${trip.origTrain ?: "?"})")
                            Text("Departs: ${trip.origDepartureTime ?: "-"} -> Arrival: ${trip.origArrivalTime ?: "-"}")
                            Text("Delay: ${trip.origDelay ?: "On Time"}")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationDropdown(
    label: String,
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it}) {
        OutlinedTextField(
            value = selected ?: "",
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true).fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { station ->
                DropdownMenuItem(
                    text = { Text(station) },
                    onClick = {
                        onSelected(station)
                        expanded = false
                    }
                )
            }
        }
    }
}