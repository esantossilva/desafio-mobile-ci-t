package com.example.moviesdbchallenge.composables.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.moviesdbchallenge.R
import com.example.moviesdbchallenge.ui.theme.MoviesDBChallengeTheme

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    fontSize: TextUnit = MaterialTheme.typography.headlineSmall.fontSize,
    textAlign: TextAlign = TextAlign.Start,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Bold,
){
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Text(
            modifier = modifier.padding(dimensionResource(R.dimen.padding_3)),
            color = textColor,
            fontSize = fontSize,
            textAlign = textAlign,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            text = text,
        )
    }
}

@Composable
fun ItemTitle(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = MaterialTheme.typography.labelLarge.fontSize,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Bold,
    maxLines: Int = 1,
    text: String,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
){
    Text(
        modifier = modifier,
        color = textColor,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun ItemSubtitle(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    fontSize: TextUnit = MaterialTheme.typography.labelMedium.fontSize,
    textAlign: TextAlign = TextAlign.Start,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
){
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        fontSize = fontSize,
        textAlign = textAlign,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun OutlineLabel(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    fontSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
    textAlign: TextAlign = TextAlign.Start,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(dimensionResource(R.dimen.padding_3)),
        text = text,
        color = textColor,
        fontSize = fontSize,
        textAlign = textAlign,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun ExpandableTextCard(
    modifier: Modifier = Modifier,
    text: String = "Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor Lorem ipsum dolor ",
) {
    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded.value = !expanded.value },
        shape = RoundedCornerShape(10.dp),
        colors = CardColors(
            containerColor = Color.White.copy(alpha = 0.1f),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    ) {
        Text(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_3))
                .animateContentSize(
                    animationSpec = tween(200, easing = FastOutSlowInEasing)
                ),
            text = text,
            maxLines = if (!expanded.value) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
        )

        ExpandableCardToggleButton(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_2)),
            toggled = expanded,
        )
    }
}

@Composable
@Preview
fun OutlineLabelPreview() {
    OutlineLabel(
        text = "Drama"
    )
}

@Composable
@Preview
fun SectionTitlePreview() {
    MoviesDBChallengeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = getBackgroundBrush())
        ) {
            SectionTitle(text = "Popular Movies")
            ItemTitle(text = "Movie Title")
            ItemSubtitle(text = "Movie subtitle")
            ExpandableTextCard()
        }
    }
}
