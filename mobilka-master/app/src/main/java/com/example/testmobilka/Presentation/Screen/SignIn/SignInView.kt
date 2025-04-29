package com.example.testmobilka.Presentation.Screen.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes

@Composable
fun SignIn(controller: NavHostController) {

    val vm = viewModel{SignInViewModel()}
    val state = vm.state
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(203, 251, 229))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp)
        ) {

            Text(
                text = "Добро пожаловать",
                color = Color.White,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .background(
                        color = Color(172, 22, 77),
                        shape = RoundedCornerShape(15.dp)
                    )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .background(
                        Color(172, 22, 77),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .width(200.dp)
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Введите логин",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = state.email,
                onValueChange = {vm.updateState(state.copy(email = it))},
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
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .background(
                        Color(172, 22, 77),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .width(200.dp)
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Введите пароль",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = state.password,
                onValueChange = {vm.updateState(state.copy(password = it)) },
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
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { vm.SignIn(context,controller) },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(172, 22, 77),
                    contentColor = Color.White)
            ) {
                Text(text = "Войти")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { controller.navigate(NavigationRoutes.SIGNUP) },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(172, 22, 77),
                    contentColor = Color.White)
            ) {
                Text(text = "Зарегистрироваться")
            }

        }
    }
}