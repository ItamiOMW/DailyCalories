package com.example.dailycalories.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R


@Composable
fun NutrientsCard(
    kCals: Float,
    carbs: Float,
    fat: Float,
    proteins: Float,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.onSurface
) {



    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = contentColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NutrientItem(
                name = stringResource(R.string.title_kCal),
                count = kCals
            )
            NutrientItem(
                name = stringResource(R.string.title_protein),
                count = proteins
            )
            NutrientItem(
                name = stringResource(R.string.title_carbs),
                count = carbs
            )
            NutrientItem(
                name = stringResource(R.string.title_fat),
                count = fat
            )
        }
    }

}

@Composable
private fun NutrientItem(
    name: String,
    count: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium)
        )
        Text(
            text = stringResource(R.string.count_grams_short, count),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}