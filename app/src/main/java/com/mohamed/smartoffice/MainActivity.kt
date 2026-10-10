package com.mohamed.smartoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohamed.smartoffice.ui.spaces.SpaceListRoute
import com.mohamed.smartoffice.ui.theme.SmartOfficeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartOfficeTheme {
                SpaceListRoute()
            }
        }
    }
}