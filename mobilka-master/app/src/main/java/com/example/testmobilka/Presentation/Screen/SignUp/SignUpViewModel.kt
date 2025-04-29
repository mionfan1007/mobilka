package com.example.testmobilka.Presentation.Screen.SignUp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testmobilka.Domain.Constants
import com.example.testmobilka.Domain.States.SignUPState
import com.example.testmobilka.Domain.models.Profile
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes
import com.example.testmobilka.Domain.Valids.isAgeValid
import com.example.testmobilka.Domain.Valids.isEmailValid
import com.example.testmobilka.Domain.Valids.isPasswordValid
import com.example.testmobilka.Domain.Valids.isPhoneValid
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _state = mutableStateOf(SignUPState())
    val state: SignUPState get() = _state.value

    fun updateState(newState: SignUPState) {
        _state.value = newState
    }

    fun SignUp(context: Context, controller: NavHostController) {
        viewModelScope.launch {
            try {
                if (state.password.isEmpty() &&
                    state.email.isEmpty() &&
                    state.phone.isEmpty() &&
                    state.age.isEmpty() &&
                    state.name.isEmpty()
                ) {
                    Toast.makeText(context, "Поля пустые", Toast.LENGTH_LONG).show()
                } else {
                    if (!state.email.isEmailValid()) {
                        Toast.makeText(
                            context,
                            "Email не соответствует паттерну",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        if (!state.password.isPasswordValid()) {
                            Toast.makeText(
                                context,
                                "В пароле используйте строчные, загл. буквы, цифры и спец. символы",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            if (!state.phone.isPhoneValid()) {
                                Toast.makeText(
                                    context,
                                    "Phone не соответствует паттерну",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (!state.age.isAgeValid()) {
                                    Toast.makeText(
                                        context,
                                        "Age не соответствует паттерну",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                else{

                                Constants.supabase.auth.signUpWith(Email) {
                                    email = state.email
                                    password = state.password}
                                    controller.navigate(NavigationRoutes.SIGNIN)
                                    val iduser = Constants.supabase.auth.currentUserOrNull()
                                    if (iduser != null) {
                                        Constants.supabase.from("profile").insert(
                                            Profile(
                                                email = state.email,
                                                name = state.name,
                                                age = state.age,
                                                phone = state.phone,
                                                image = null,
                                                id = iduser.id
                                            )
                                        )


                                    }
                                }
                            }
                        }

                    }
                }





            } catch (e: Exception) {
                Log.d("Не удалось зарегистрироваться", e.message.toString())
            }

        }
    }
}