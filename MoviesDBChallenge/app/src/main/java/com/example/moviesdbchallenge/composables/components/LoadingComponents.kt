package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesdbchallenge.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LoadingWidget()
    }
}

@Composable
fun LoadingWidget() {
    CircularProgressIndicator(
        color = colorResource(R.color.teal_700),
        trackColor = colorResource(R.color.teal_900),
        strokeCap = StrokeCap.Round
    )
}

@Composable
@Preview
fun LoadingPreview() {
    LoadingScreen()
}
