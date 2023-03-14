package com.example.dailycalories.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R


@Composable
fun VerticalNutrientsCard(
    carbs: Float,
    fat: Float,
    proteins: Float,
    dailyCarbs: Float,
    dailyFat: Float,
    dailyProteins: Float,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.onSurface,
) {

    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = contentColor,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NutrientItem(
                name = stringResource(R.string.title_protein),
                count = proteins,
                countInTotal = dailyProteins
            )
            NutrientItem(
                name = stringResource(R.string.title_carbs),
                count = carbs,
                countInTotal = dailyCarbs
            )
            NutrientItem(
                name = stringResource(R.string.title_fat),
                count = fat,
                countInTotal = dailyFat
            )
        }
    }

}

@Composable
fun HorizontalNutrientsCard(
    carbs: Float,
    fat: Float,
    proteins: Float,
    dailyCarbs: Float,
    dailyFat: Float,
    dailyProteins: Float,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.onSurface,
    shape: Shape = RoundedCornerShape(15.dp),
) {

    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = contentColor,
        elevation = 0.dp,
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NutrientItem(
                name = stringResource(R.string.title_protein),
                count = proteins,
                countInTotal = dailyProteins
            )
            NutrientItem(
                name = stringResource(R.string.title_carbs),
                count = carbs,
                countInTotal = dailyCarbs
            )
            NutrientItem(
                name = stringResource(R.string.title_fat),
                count = fat,
                countInTotal = dailyFat
            )
        }
    }

}

@Composable
private fun NutrientItem(
    name: String,
    count: Float,
    countInTotal: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Medium),
            textAlign = TextAlign.Start
        )
        Text(
            text = stringResource(R.string.count_grams_short, count),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.count_grams_short, countInTotal),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
            color = Color.Unspecified.copy(alpha = 0.5f),
            textAlign = TextAlign.Start
        )
    }
}