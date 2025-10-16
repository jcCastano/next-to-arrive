package com.example.nexttoarrive

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Surface { App(modifier = Modifier.padding(top = 32.dp), driver = DatabaseDriverFactory(this).createDriver()) }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(driver = DatabaseDriverFactory(LocalContext.current).createDriver())
}