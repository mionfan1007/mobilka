package com.example.testmobilka.Presentation.Screen.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testmobilka.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kotlin.math.cos

@Composable
fun MainView(controller: NavHostController) {
    val vm = viewModel { MainViewModel() }
    val state = vm.state
    val context = LocalContext.current
    val searchBar = remember { mutableStateOf("") }
    val posters = vm.posters.observeAsState(emptyList())
    val selectedCategory = remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) {
        vm.getData(context)
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
            FieldSearch(
                value = searchBar.value,
                onvaluechange = { newText ->
                    searchBar.value = newText
                    vm.filterList(
                        searchBar.value,
                        selectedCategory.intValue
                    )
                }
            )

            LazyRow {
                items(state.allCategories) { index ->

                    CategoryItem(index.category,false){

                    }
                }
            }
            LazyColumn {
                items(state.allPosters){ items ->

                    PosterItem(items.image,items.description,items.ticket_price)
                }
            }
        }
    }
}


@Composable
fun FieldSearch(value: String, onvaluechange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { onvaluechange(it) },
        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.DarkGray,
            unfocusedContainerColor = Color(24, 226, 186),
            focusedContainerColor = Color(43, 233, 195)

        ),
        placeholder = {
            Text(
                "Введите текст",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text

        ),
    )
}

@Composable
fun PosterItem(img:String,title:String,cost:Int,) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(24, 226, 186), shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .fillMaxWidth()
            .width(200.dp),

        contentAlignment = Alignment.Center
    ) {


        Column(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {

            Text(text = title)
            Text(text = cost.toString() + " рублей")
        }
    }
}
@Composable
fun CategoryItem(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) Color(24, 226, 186) else Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .background(Color.White, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .width(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, color = Color(172, 22, 77), textAlign = TextAlign.Center)
    }
}