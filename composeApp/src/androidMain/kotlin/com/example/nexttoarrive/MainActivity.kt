package com.example.nexttoarrive

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexttoarrive.api.ApiClient
import com.example.nexttoarrive.data.NextToArriveRepository
import com.example.nexttoarrive.db.NtaDatabase
import com.example.nexttoarrive.presentation.NtaViewModel
import com.example.nexttoarrive.sql.DatabaseDriverFactory
import com.example.nexttoarrive.ui.NtaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Surface { App(DatabaseDriverFactory(this).createDriver()) }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(DatabaseDriverFactory(LocalContext.current).createDriver())
}