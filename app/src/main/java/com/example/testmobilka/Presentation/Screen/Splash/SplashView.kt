package com.example.testmobilka.Presentation.Screen.Splash

import android.media.Image
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.navigation.NavHostController
import com.example.testmobilka.Presentation.Navigation.NavigationRoutes
import com.example.testmobilka.R
import kotlinx.coroutines.delay
import java.util.Vector

@Composable
fun SplashView(controller: NavHostController) {
    val image = painterResource(R.drawable.circus)
    val anim = remember {
        androidx.compose.animation.core.Animatable(2f)
    }

    LaunchedEffect(Unit) {

        anim.animateTo(
            targetValue = 4f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMedium)
        )

        delay(3000)
        controller.navigate(NavigationRoutes.SIGNIN) {
            popUpTo(NavigationRoutes.SPLASH) {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Image(

            painter = image, contentDescription = "circus image",
                    modifier = Modifier.scale(anim.value)
        )


    }
}


