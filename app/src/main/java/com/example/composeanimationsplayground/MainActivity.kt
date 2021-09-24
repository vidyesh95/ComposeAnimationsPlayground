package com.example.composeanimationsplayground

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeanimationsplayground.ui.theme.ComposeAnimationsPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    ComposeAnimationsPlaygroundTheme {
        var sizeState by remember {
            mutableStateOf(200.dp)
        }

        val size by animateDpAsState(
            targetValue = sizeState,
            /*animationSpec = tween(
                durationMillis = 3000,
                delayMillis = 300,
                easing = LinearEasing
            ),*/
            /*animationSpec = spring(
                Spring.DampingRatioHighBouncy
            ),*/
            /*keyframes {
                durationMillis=5000
                sizeState at 0 with LinearEasing
                sizeState * 1.5f at 1000 with FastOutLinearInEasing
                sizeState * 2f at 5000
            }*/
            tween(
                durationMillis = 1000
            )
        )

        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        // A surface container using the 'background' color from the theme
        /*Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .size(size = size)
                    .background(color = color),
                contentAlignment = Alignment.Center
            ) {
                Button(enabled = true, onClick = { sizeState += 50.dp }) {
                    Text(text = "Increase size")
                }
            }
        }*/

        Box(
            modifier = Modifier
                .size(size = size)
                .background(color = color),
            contentAlignment = Alignment.Center
        ) {
            Button(enabled = true, onClick = { sizeState += 50.dp }) {
                Text(text = "Increase size")
            }
        }
    }
}

@Preview(showBackground = true, name = "Light mode")
@Preview(showBackground = true, name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MyApp()
}