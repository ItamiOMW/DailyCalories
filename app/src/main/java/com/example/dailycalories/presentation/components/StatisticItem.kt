package com.example.dailycalories.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R

@Composable
fun StatisticItem(
    name: String,
    count: Float,
    totalCount: Float,
    modifier: Modifier,
) {

    val percentage = remember(count, totalCount) {
        count / totalCount
    }

    val animatedPercentage by animateFloatAsState(targetValue = percentage)

    Column(modifier = modifier) {
        Text(
            text = stringResource(
                id = R.string.count_consumed_and_need_to_consume,
                count.toInt(), totalCount.toInt()
            ),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(2.dp))
        LinearProgressIndicator(
            progress = if (animatedPercentage > 1f) 1f else animatedPercentage,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.fillMaxWidth(),
        )
    }

}