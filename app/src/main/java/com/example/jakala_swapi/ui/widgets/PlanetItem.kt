package com.example.jakala_swapi.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jakala_swapi.data.model.PlanetResponse
import com.example.jakala_swapi.helper.UiStateProvider

@Composable
@Preview(showBackground = true)
fun PlanetItem(
    modifier: Modifier = Modifier,
    planet: PlanetResponse.Planet = UiStateProvider.defaultPlanet(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(24.dp)

    ) {
        Text(
            text = "Name: ${planet.name}", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(text = "Rotation Period: ${planet.rotationPeriod}")
        Text(text = "Orbital Period: ${planet.orbitalPeriod}")
        Text(text = "Climate: ${planet.climate}")
        Text(text = "Gravity: ${planet.gravity}")
        Text(text = "Diameter: ${planet.diameter}")
    }
}