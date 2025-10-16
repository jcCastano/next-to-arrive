package com.example.nexttoarrive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.sqldelight.db.SqlDriver
import com.example.nexttoarrive.api.ApiClient
import com.example.nexttoarrive.data.NextToArriveRepository
import com.example.nexttoarrive.db.NtaDatabase
import com.example.nexttoarrive.db.NtaDatabase.Companion.invoke
import com.example.nexttoarrive.presentation.NtaViewModel
import com.example.nexttoarrive.sql.DatabaseDriverFactory
import com.example.nexttoarrive.ui.NtaScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import nexttoarrive.composeapp.generated.resources.Res
import nexttoarrive.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(driver: SqlDriver) {
    MaterialTheme {
        val db = NtaDatabase(driver)
        val repo = NextToArriveRepository(db, ApiClient())
        val vm = NtaViewModel(repo)

        Surface(Modifier.fillMaxSize()) {
            NtaScreen(vm)
        }
    }
}