package com.example.nexttoarrive.api

sealed interface Query

data class NextToArriveQuery(
    val origin: String,
    val destination: String,
    val maxResults: Int = 3
): Query
