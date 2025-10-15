package com.example.nexttoarrive.data

import com.example.nexttoarrive.api.ApiClient
import com.example.nexttoarrive.api.NextToArriveQuery
import com.example.nexttoarrive.db.NtaDatabase
import com.example.nexttoarrive.model.NextToArriveResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class NextToArriveRepository(
    private val db: NtaDatabase,
    private val api: ApiClient,
    private val json: Json = Json { ignoreUnknownKeys = true; encodeDefaults = true }
) {
    @OptIn(ExperimentalTime::class)
    suspend fun fetch(q: NextToArriveQuery): NextToArriveResponse =
        withContext(Dispatchers.Default) {
            val res = api.nextToArrive(q)
            val raw = json.encodeToString(NextToArriveResponse.serializer(), res)
            db.arrivalsQueries.insertCache(
                q.origin,
                q.destination,
                raw,
                Clock.System.now().toEpochMilliseconds())
            res
        }

    suspend fun lastCached(origin: String, destination: String): NextToArriveResponse? =
        withContext(Dispatchers.Default) {
            val row = db.arrivalsQueries.getLatest(origin, destination).executeAsOneOrNull()
            row?.json?.let { json.decodeFromString(NextToArriveResponse.serializer(), it) }
        }
}