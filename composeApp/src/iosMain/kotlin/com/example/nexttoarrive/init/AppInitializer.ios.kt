package com.example.nexttoarrive.init

import com.example.nexttoarrive.di.allModules
import com.example.nexttoarrive.di.appModule
import com.example.nexttoarrive.di.platformModule
import org.koin.core.context.startKoin

actual class AppContext

actual object AppInitializer {
    actual fun init(app: AppContext) {
        startKoin {
            modules(allModules)
        }
    }
}