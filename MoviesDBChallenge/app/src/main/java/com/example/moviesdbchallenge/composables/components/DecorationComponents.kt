package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.moviesdbchallenge.R

@Composable
fun DefaultVerticalDivider(
    modifier: Modifier = Modifier,
    height: Dp = 24.dp,
    thickness: Dp = 1.dp
) {
    VerticalDivider(
        modifier = modifier.height(height),
        thickness = thickness,
        color = Color.Gray,
    )
}

@Composable
fun ExpandableCardToggleButton(
    modifier: Modifier = Modifier,
    toggled: MutableState<Boolean> = mutableStateOf(false)
) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_2))
            .clickable { toggled.value = !toggled.value },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)

        Icon(
            imageVector =
                if (!toggled.value) Icons.Default.KeyboardArrowDown
                else Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            tint = Color.Gray,
        )

        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
    }
}

@Composable
@Preview
fun DefaultVerticalDividerPreview() {
    DefaultVerticalDivider()
}

@Composable
@Preview
fun ExpandableCardToggleButtonPreview() {
    ExpandableCardToggleButton()
}
