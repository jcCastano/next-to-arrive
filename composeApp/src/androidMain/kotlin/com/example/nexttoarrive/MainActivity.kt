package com.example.nexttoarrive

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
import com.example.nexttoarrive.init.AppInitializer
import com.example.nexttoarrive.sql.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppInitializer.init(application)

        setContent {
            Surface { SharedApp.Context(modifier = Modifier.padding(top = 32.dp), driver = DatabaseDriverFactory(this).createDriver()) }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    SharedApp.Context(driver = DatabaseDriverFactory(LocalContext.current).createDriver())
}