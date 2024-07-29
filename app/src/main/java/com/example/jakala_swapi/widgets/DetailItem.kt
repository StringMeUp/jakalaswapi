package com.example.jakala_swapi.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jakala_swapi.R
import com.example.jakala_swapi.data.model.MovieDetail
import com.example.jakala_swapi.helper.UiStateProvider

@Preview(showBackground = true)
@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail = UiStateProvider.defaultMovieDetail(),
) {

    val boldStyle = SpanStyle(
        fontSize = 24.sp,
        color = colorResource(id = R.color.black),
        fontWeight = FontWeight.ExtraBold
    )

    val normalStyle = SpanStyle(
        fontSize = 16.sp,
        color = colorResource(id = R.color.black),
        fontWeight = FontWeight.Normal
    )
    val text = buildAnnotatedString {
        withStyle(boldStyle) {
            append(movieDetail.title)
            append("\n")
            append("\n")
        }

        withStyle(normalStyle) {
            append(movieDetail.openingCrawl)
            append("\n")
            append("\n")
        }

        withStyle(normalStyle) {
            append("Release date: ${movieDetail.releaseDate}")
        }

    }
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}