package com.example.nexttoarrive.api

import com.example.nexttoarrive.model.NextToArriveResponse
import com.example.nexttoarrive.model.NtaTrip
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


internal object Http {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    val client = HttpClient {
        install(ContentNegotiation) { json(json) }
        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    println("Ktor: $message")
                }
            }
            level = LogLevel.BODY
        }
    }
}

class ApiClient(
    private val client: HttpClient = Http.client
) {
    suspend fun nextToArrive(q: NextToArriveQuery): NextToArriveResponse {
        val url = URLBuilder("https://www3.septa.org/api/NextToArrive/index.php").apply {
            parameters.append("req1", q.origin)
            parameters.append("req2", q.destination)
            parameters.append("req3", q.maxResults.toString())
        }.build()

        val trips: List<NtaTrip> = client.get(url).body()
        return NextToArriveResponse(trips)
    }
}