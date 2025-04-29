package com.example.testmobilka.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testmobilka.Presentation.Screen.DetailsPoster.DetailsView
import com.example.testmobilka.Presentation.Screen.Main.MainView
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
            MainView(controller)
        }


        composable(
            route = NavigationRoutes.DETAILS + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            val id = it.arguments?.getString("id")

            if (id != null) {
                DetailsView(controller, id)
            }
        }
    }
}


