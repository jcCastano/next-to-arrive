package com.example.nexttoarrive

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.example.nexttoarrive.sql.DatabaseDriverFactory

fun MainViewController() = ComposeUIViewController { SharedApp.Context(modifier = Modifier.padding(top = 64.dp),DatabaseDriverFactory().createDriver()) }