package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.domain.models.CastModel
import com.example.moviesdbchallenge.R


@Composable
fun CastList(
    modifier: Modifier = Modifier,
    castList: List<CastModel>,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_3))
    ) {
        items(castList) {
            CastCard(cast = it)
        }
    }
}

@Composable
fun CastCard(
    modifier: Modifier = Modifier,
    cast: CastModel? = null,
    contentDescription: String = "",
    placeholder: Painter = painterResource(R.drawable.person_placeholder),
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_3))),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
    ) {
        cast?.let {
            AsyncImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.cast_card_height))
                    .width(dimensionResource(R.dimen.cast_card_width)),
                model = it.imageUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                placeholder = placeholder,
                error = placeholder,
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(dimensionResource(R.dimen.cast_card_width))
                    .padding(vertical = dimensionResource(R.dimen.padding_3)),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
            ) {
                ItemTitle(text = it.name)

                VerticalSpacer(size = dimensionResource(R.dimen.padding_1))

                ItemSubtitle(text = it.character)
            }
        }
    }
}

@Composable
@Preview
fun CastItemPreview() {
    val cast = CastModel(
        name = "Artist name",
        character = "Character name aaaaaaaaaa"
    )

    CastCard(cast = cast)
}

@Composable
@Preview
fun CastListPreview() {
    val cast = CastModel(
        name = "Artist name",
        character = "Character name aaaaaaaaaa"
    )

    val list = listOf(
        cast,
        cast,
        cast,
        cast,
        cast,
    )

    CastList(castList = list)
}
