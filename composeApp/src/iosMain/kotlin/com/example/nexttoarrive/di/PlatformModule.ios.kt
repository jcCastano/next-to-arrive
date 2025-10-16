package com.example.nexttoarrive.di

import com.example.nexttoarrive.sql.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseDriverFactory() }
    single { get<DatabaseDriverFactory>().createDriver() }
}