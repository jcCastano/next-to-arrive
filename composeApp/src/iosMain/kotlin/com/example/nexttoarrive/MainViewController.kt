package com.example.nexttoarrive

import androidx.compose.ui.window.ComposeUIViewController
import com.example.nexttoarrive.sql.DatabaseDriverFactory

fun MainViewController() = ComposeUIViewController { App(DatabaseDriverFactory().createDriver()) }