package com.example.testmobilka.Presentation.Screen.DetailsPoster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testmobilka.Presentation.Screen.Main.MainViewModel

@Composable
fun DetailsView(controller: NavHostController, id:String) {
    val vm = viewModel { DetailsViewModel(id) }
    val state = vm.state
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.getPoster(context)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(203, 251, 229))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            androidx.compose.material3.Text(text = "Страница деталей книги")
        }


    }
}