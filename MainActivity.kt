package com.uvg.labfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uvg.labfinal.navigation.AppNav
import com.uvg.labfinal.ui.theme.LabfinalTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge content rendering for a modern design
        enableEdgeToEdge()

        // Set the content view of the activity
        setContent {
            // Apply the application's theme
            LabfinalTheme {
                // Setup navigation for the app
                MainContent()
            }
        }
    }
}


@Composable
fun MainContent() {
    AppNav(
        modifier = Modifier.fillMaxSize() // Ensures the navigation occupies the full screen
    )
}
