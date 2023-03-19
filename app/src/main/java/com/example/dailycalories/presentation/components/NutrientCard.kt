package com.example.dailycalories.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.utils.round


@Composable
fun NutrientCard(
    name: String,
    count: Float,
    countInTotal: Float,
    modifier: Modifier = Modifier,
    progressBarColor: Color,
    shape: Shape = RoundedCornerShape(15.dp),
) {
    val percentage = remember(count, countInTotal) {
        count / countInTotal
    }
    Card(
        elevation = 0.dp,
        modifier = modifier,
        shape = shape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Medium),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                text = stringResource(
                    R.string.count_consumed_and_need_to_consume,
                    count.round(1),
                    countInTotal.round(1)
                ),
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface,
            )
            CircularProgressBar(
                percentage = percentage,
                color = progressBarColor,
                secondColor = MaterialTheme.colors.onSurface,
                radius = 33.dp,
                strokeWidth = 6.dp
            )
        }
    }

}