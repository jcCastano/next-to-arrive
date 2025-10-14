package com.example.nexttoarrive

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform