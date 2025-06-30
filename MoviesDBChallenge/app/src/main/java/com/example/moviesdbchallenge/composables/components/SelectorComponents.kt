package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesdbchallenge.R
import com.example.moviesdbchallenge.ui.theme.MoviesDBChallengeTheme

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    useFixedWidth: Boolean = false,
    itemWidth: Dp = 120.dp,
    cornerRadius: Int = 24,
    onItemSelection: (selectedItemIndex: Int) -> Unit = {}
) {
    val selectedIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }
    val itemIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(38.dp),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Row(
            modifier = modifier
                .wrapContentWidth()
                .background(colorResource(R.color.green_900)),
        ) {
            items.forEachIndexed { index, item ->
                itemIndex.intValue = index
                Card(
                    modifier = if (useFixedWidth)
                        modifier.width(itemWidth).padding(dimensionResource(R.dimen.padding_1))
                    else
                        modifier.weight(1f).padding(dimensionResource(R.dimen.padding_1)),
                    onClick = {
                        selectedIndex.intValue = index
                        onItemSelection(selectedIndex.intValue)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedIndex.intValue == index) {
                            colorResource(R.color.green_300)
                        } else {
                            colorResource(R.color.green_900)
                        },
                        contentColor = if (selectedIndex.intValue == index)
                            MaterialTheme.colorScheme.scrim
                        else
                            MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = RoundedCornerShape(
                        percent =
                        when (index) {
                            0, items.size - 1 -> cornerRadius
                            else -> 0
                        }
                    ),
                ) {
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(
                            text = item,
                            style = LocalTextStyle.current.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (selectedIndex.intValue == index)
                                    Color.Black.copy(alpha = 0.7f)
                                else
                                    Color(0xFFFAFAFA)
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun SelectorWidgetPreview() {
    MoviesDBChallengeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.teal_900),
                            colorResource(R.color.black)
                        ),
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_3))
            ) {
                SegmentedControl(
                    modifier = Modifier,
                    listOf("All", "Upcoming")
                )
            }
        }
    }
}
