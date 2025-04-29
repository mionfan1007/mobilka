package com.example.testmobilka.Presentation.Screen.SignIn

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testmobilka.Domain.Constants
import com.example.testmobilka.Domain.States.SignINState
import com.example.testmobilka.Domain.Valids.isEmailValid
import com.example.testmobilka.Domain.Valids.isPasswordValid
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class SignInViewModel:ViewModel() {

    private val _state = mutableStateOf(SignINState())
    val state: SignINState get() = _state.value

    fun updateState(newState:SignINState){_state.value = newState }

    fun SignIn(context: Context,controller:NavHostController){
        viewModelScope.launch {
            try {
                if (state.password.isEmpty() && state.email.isEmpty()){
                    Toast.makeText(context,"Поля пустые",Toast.LENGTH_LONG).show()
                }
                else{
                    if (state.email.isEmailValid()){
                        if (state.password.isPasswordValid()){
                            Constants.supabase.auth.signOut()
                            Constants.supabase.auth.signInWith(Email){
                                email   = state.email
                                password = state.password
                            }
                            controller.navigate(NavigationRoutes.AUTHORISED)
                        }
                        else{
                            Toast.makeText(context,"Password не соответствует паттерну",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(context,"Email не соответствует паттерну",Toast.LENGTH_LONG).show()
                    }
                }

            }catch (e:Exception){
                Toast.makeText(context,"Не удалось авторизоваться",Toast.LENGTH_LONG).show()
            }
        }
    }
}