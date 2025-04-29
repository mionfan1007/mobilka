package com.example.testmobilka.Presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.testmobilka.Presentation.Navigation.Navigation
import com.example.testmobilka.Presentation.ui.theme.TestMobilkaTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestMobilkaTheme {
                val controller = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Navigation(controller)
                }
            }
        }
    }
}
