package com.example.nexttoarrive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.sqldelight.db.SqlDriver
import com.example.nexttoarrive.presentation.NtaViewModel
import com.example.nexttoarrive.ui.NtaScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SharedApp: KoinComponent {
    @Composable
    @Preview
    fun Context(modifier: Modifier = Modifier, driver: SqlDriver) {
        MaterialTheme {
            val vm by inject<NtaViewModel>()

            Surface(Modifier.fillMaxSize()) {
                Box(modifier) {
                    NtaScreen(vm)
                }
            }
        }
    }
}

