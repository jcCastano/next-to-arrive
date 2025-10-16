package com.example.nexttoarrive.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NextToArriveResponse(
    val trips: List<NtaTrip>
)

@Serializable
data class NtaTrip(
    @SerialName("orig_train")
    val origTrain: String? = null, // Initial regional rail number of the train passing through the requested source station

    @SerialName("orig_line")
    val origLine: String? = null, // Initial regional rail line where the requested source station is

    @SerialName("orig_departure_time")
    val origDepartureTime: String? = null, // Departure time of the initial regional rail at the requested source station

    @SerialName("orig_arrival_time")
    val origArrivalTime: String? = null, // Arrival time of the regional rail at the requested destination or the connecting station

    @SerialName("orig_delay")
    val origDelay: String? = null, // Current status of the intial regional rail

    @SerialName("term_train")
    val termTrain: String? = null, // Regional rail number of the connecting train towards the requested destination

    @SerialName("term_line")
    val termLine: String? = null, // Connecting regional rail line where the requested destination station is

    @SerialName("term_depart_time")
    val termDepartTime: String? = null, // Departure time of the connecting regional rail from the connecting station

    @SerialName("term_arrival_time")
    val termArrivalTime: String? = null, // Arrival time of the connecting regional rail at the requested destination

    @SerialName("Connection")
    val connection: String? = null, // Connecting station name

    @SerialName("term_delay")
    val termDelay: String? = null, // Current status of the connecting regional rail

    @SerialName("isdirect")
    val isDirect: Boolean? // Indicates if the journey is direct or not
)
