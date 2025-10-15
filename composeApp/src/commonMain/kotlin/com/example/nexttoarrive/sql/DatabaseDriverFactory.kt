package com.example.nexttoarrive.sql

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(dbName: String = "nta.db"): SqlDriver
}