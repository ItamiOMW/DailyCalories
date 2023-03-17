package com.example.dailycalories.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.dailycalories.R
import com.example.dailycalories.utils.round

@Composable
fun CountGramsAndTextItem(
    count: Float,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    countFontSize: TextUnit = 20.sp,
    textFontSize: TextUnit = 16.sp,
) {

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.count_grams_short_2f, count.round(1)),
            style = MaterialTheme.typography.h6.copy(fontSize = countFontSize),
            color = color,
            textAlign = TextAlign.Center
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(fontSize = textFontSize),
            color = color,
            textAlign = TextAlign.Center,
        )
    }

}