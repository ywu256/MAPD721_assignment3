package com.yulingwu.mapd721_assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yulingwu.mapd721_assignment3.ui.theme.MAPD721_assignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAPD721_assignment3Theme {
                Navigation()
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("screen1") }) { Text("Screen 1: Animated Content") }
        Button(onClick = { navController.navigate("screen2") }) { Text("Screen 2: Value-Based Animation 1") }
        Button(onClick = { navController.navigate("screen3") }) { Text("Screen 3: Value-Based Animation 2") }
        Button(onClick = { navController.navigate("screen4") }) { Text("Screen 4: Gesture-Based Animation") }

        Spacer(modifier = Modifier.height(300.dp))

        Text(text = "Student Name: Yu-Ling Wu", fontSize = 18.sp)
        Text(text = "Student ID: 301434538", fontSize = 18.sp)
    }
}

@Composable
fun AnimatedContentScreen() {
    var isVisible by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Toggle Content")
        }

        AnimatedContent(targetState = isVisible, label = "AnimatedContent") { target ->
            if (target) {
                Text(text = "Hello, Compose!", fontSize = 24.sp, color = Color.Blue)
            } else {
                Text(text = "Goodbye, Compose!", fontSize = 24.sp, color = Color.Red)
            }
        }
    }
}

@Composable
fun ValueBasedAnimationScreen1() {
    var visible by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Animation")
        }

        AnimatedVisibility(visible = visible) {
            Text(
                text = "Fading In & Out!",
                fontSize = 24.sp,
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(16.dp)
                    .animateContentSize()
            )
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(navController) }
        composable("screen1") { AnimatedContentScreen() }
        composable("screen2") { ValueBasedAnimationScreen1() }
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
    MAPD721_assignment3Theme {
        Greeting("Android")
    }
}