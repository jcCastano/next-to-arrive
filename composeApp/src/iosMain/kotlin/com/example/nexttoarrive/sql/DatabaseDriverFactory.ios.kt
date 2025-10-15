package com.example.nexttoarrive.sql

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.nexttoarrive.db.NtaDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(dbName: String): SqlDriver =
        NativeSqliteDriver(NtaDatabase.Schema, dbName)
}