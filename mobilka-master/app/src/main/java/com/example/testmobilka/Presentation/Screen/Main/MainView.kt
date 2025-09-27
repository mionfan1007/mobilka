package com.example.testmobilka.Presentation.Screen.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.testmobilka.Domain.models.Posters
import com.example.testmobilka.Domain.models.category
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes

@Composable
fun MainView(controller: NavHostController) {
    val vm = viewModel { MainViewModel() }
    val state = vm.state
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.loadPosters(context)
    }


    val filtedList = remember(key1 = state.ListPoster, key2 = state.selectedID, key3 = state.searchState) {
        var result = if (state.selectedID.id==0){
            state.ListPoster
        }else{
            state.ListPoster.filter { it.category == state.selectedID.id }
        }

        if(state.searchState.length>=3){
            val search = state.searchState.lowercase()
            result = result.filter { post->
                post.description.lowercase().contains(search)
            }
        }
        result
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
            //!!!!!!!!!!!!
            FieldSearch(value = state.searchState)
            {
                vm.updateState(state.copy(searchState = it))
            }

            LazyRow {
                items(state.CategoryList) { index ->

                    CategoryItem(catego = index, state.selectedID.id == index.id)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            LazyColumn {
                items(filtedList){ items ->

                    PosterItem(items, state.CategoryList, controller)
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
        maxLines = 1,
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
fun PosterItem(poster: Posters, category: List<category>, controller: NavHostController) {
    val vm = viewModel { MainViewModel() }
    val state = vm.state
    val imgState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current).size(size = Size.ORIGINAL)
            .data(poster.image).build()
    ).state
    Box(
        modifier = Modifier

            .padding(10.dp)
            .background(Color(24, 226, 186), shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .fillMaxWidth()
            .width(200.dp)
            .clickable(onClick = {
                controller.navigate(NavigationRoutes.DETAILS + "/" + poster.id )
            }),
        contentAlignment = Alignment.Center,

    ) {


        Column(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
            if (imgState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = imgState.painter,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else if (imgState is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            }


            Text(text = poster.description)
            Text(text = poster.ticket_price.toString() + " рублей")
            Text(text = category[poster.category].category)
        }
    }

}
@Composable
fun CategoryItem(catego: category, selected:Boolean) {
    val vm = viewModel { MainViewModel() }
    val state = vm.state
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 0.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = {vm.updateState(
                state.copy(
                    selectedID = category(
                        catego.id,
                        catego.category
                    )
                )
            )
            },
                )
            .background(Color.White, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .width(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = catego.category, color = Color(172, 22, 77), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
    }
}