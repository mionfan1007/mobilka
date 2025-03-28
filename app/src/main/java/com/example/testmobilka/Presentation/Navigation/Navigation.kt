package com.example.testmobilka.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmobilka.Presentation.Screen.Authorised.Authorised
import com.example.testmobilka.Presentation.Screen.SignIn.SignIn
import com.example.testmobilka.Presentation.Screen.SignUp.SignUpView
import com.example.testmobilka.Presentation.Screen.Splash.SplashView

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = NavigationRoutes.SPLASH) {
        composable(NavigationRoutes.SPLASH) {
            SplashView(controller)
        }

        composable(NavigationRoutes.SIGNIN) {
            SignIn(controller)
        }

        composable(NavigationRoutes.SIGNUP) {
            SignUpView(controller)
        }
        composable(NavigationRoutes.AUTHORISED){
            Authorised(controller)
        }
    }
}


