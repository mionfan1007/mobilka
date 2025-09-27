package com.example.testmobilka.Presentation.Screen.DetailsPoster

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun DetailsView(controller: NavHostController, id:String) {
    val vm = viewModel { DetailsViewModel(id) }
    val state = vm.state
    val imgState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current).size(size = Size.ORIGINAL)
            .data(state.image).build()
    ).state

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
            TextField(modifier = Modifier, colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(24, 226, 186),
                unfocusedContainerColor = Color(203, 251, 229)
            ), value = state.description, onValueChange = { vm.UpdState(state.copy(description = it)) })

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .background(Color(24, 226, 186))
                    .padding(10.dp)
            )
            {
                if (imgState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = imgState.painter,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                } else if (imgState is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(20.dp))


            var category = state.CategoryList.find { it.id == state.category }
            var expanded by remember { mutableStateOf(false) }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                state.CategoryList.forEach { category ->
                    if (category.id > 0) {
                        DropdownMenuItem(
                            text = { Text(text = category.category) },
                            onClick = {
                                vm.UpdState(state.copy(category = category.id))
                                expanded = false
                            }
                        )
                    }
                }
            }

            Row (modifier = Modifier
                .background(Color.White)
                .align(Alignment.CenterHorizontally)) {
                category?.category?.let { Text(text = it) }
                Spacer(modifier = Modifier.width(50.dp))
                IconButton(onClick = { expanded = true }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
                }
            }
        }

        Button(
            onClick = { vm.UpdatePosters(controller) }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(172, 22, 77))

        ) {
            Text(text = "Сохранить изменения")
        }



    }
}
