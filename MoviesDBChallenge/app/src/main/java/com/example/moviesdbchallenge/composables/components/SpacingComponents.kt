package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.moviesdbchallenge.R

@Composable
fun VerticalSpacer(
    modifier: Modifier = Modifier,
    size: Dp = dimensionResource(R.dimen.padding_2),
) {
    Spacer(modifier = modifier.height(size))
}

@Composable
fun HorizontalSpacer(
    modifier: Modifier = Modifier,
    size: Dp = dimensionResource(R.dimen.padding_2),
) {
    Spacer(modifier = modifier.width(size))
}
