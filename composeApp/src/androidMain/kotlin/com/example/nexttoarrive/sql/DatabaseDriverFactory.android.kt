package com.example.nexttoarrive.sql

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.nexttoarrive.db.NtaDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(dbName: String): SqlDriver =
        AndroidSqliteDriver(NtaDatabase.Schema, context, dbName)
}