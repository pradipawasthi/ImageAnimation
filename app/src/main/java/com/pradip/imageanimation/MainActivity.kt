package com.pradip.imageanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pradip.imageanimation.ui.theme.ImageAnimationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageAnimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    RotatingImage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageAnimationTheme {
        Greeting("Android")
    }
}


//@Composable
//fun RotatingImage(modifier: Modifier = Modifier) {
//    val infiniteTransition = rememberInfiniteTransition()
//
//    // Animating rotation between 0 and 90 degrees
//    val rotation by infiniteTransition.animateFloat(
//        initialValue = 90f,
//        targetValue = 0f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 1000), // Duration of the rotation
//            repeatMode = RepeatMode.Reverse // Reverses the animation to go back to 0 degrees
//        )
//    )
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(id = R.drawable.loader), // Use your drawable resource
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize()
//                .rotate(rotation)
//        )
//    }
//}


@Composable
fun RotatingImage(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animating rotation along the Y-axis between 0 and 180 degrees and back
    val rotationy by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 45f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000), // Duration of the rotation
            repeatMode = RepeatMode.Reverse // Reverse the animation back to 0 degrees
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loader), // Use your drawable resource
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotationy
                    cameraDistance = 12f * density
                }
        )
    }
}