package com.example.nexttoarrive.di

import app.cash.sqldelight.db.SqlDriver
import com.example.nexttoarrive.sql.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseDriverFactory(get()) }
    single { get<DatabaseDriverFactory>().createDriver() }
}