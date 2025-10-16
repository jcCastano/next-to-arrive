package com.example.nexttoarrive.init

import org.koin.core.module.Module

expect class AppContext

expect object AppInitializer {
    fun init(app: AppContext)
}
