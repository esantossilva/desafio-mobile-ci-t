package com.example.moviesdbchallenge

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.moviesdbchallenge.composables.components.StatusBarProtection
import com.example.moviesdbchallenge.navigation.MovieNavGraph
import com.example.moviesdbchallenge.ui.theme.MoviesDBChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(getColor(R.color.navigation_black))
        )

        setContent {
            MoviesDBChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.teal_900),
                            colorResource(R.color.black)
                        )
                    )
                )) { innerPadding ->
                    val padding = innerPadding

                    val navController = rememberNavController()
                    MovieNavGraph(navController)

                    StatusBarProtection()
                }
            }
        }
    }
}
