package com.example.nexttoarrive.init

import android.app.Application
import com.example.nexttoarrive.di.allModules
import com.example.nexttoarrive.di.appModule
import com.example.nexttoarrive.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual typealias AppContext = Application

actual object AppInitializer {
    actual fun init(app: AppContext) {
        startKoin {
            androidContext(app)
            modules(allModules)
        }
    }
}

