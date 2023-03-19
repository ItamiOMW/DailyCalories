package com.example.dailycalories.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R


@Composable
fun CircularProgressBar(
    percentage: Float,
    radius: Dp = 50.dp,
    color: Color = MaterialTheme.colors.primary,
    secondColor: Color = MaterialTheme.colors.primaryVariant,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {

    val currentPercentage = remember { Animatable(0f) }

    LaunchedEffect(percentage) {
        currentPercentage.animateTo(
            percentage,
            animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.Center)
        ) {
            drawArc(
                color = secondColor,
                startAngle = -90f,
                sweepAngle = 360 * 1f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.Center)
        ) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = stringResource(R.string.percent_progress, (percentage * 100).toInt()),
            color = secondColor
        )
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    radius: Dp = 50.dp,
    color: Brush = Brush.verticalGradient(colors = listOf(MaterialTheme.colors.primary)),
    secondColor: Color = MaterialTheme.colors.primaryVariant,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {

    val currentPercentage = remember { Animatable(0f) }

    val percentageAnimateTo = if (percentage == 0f) 0.0f else percentage

    LaunchedEffect(percentage) {
        currentPercentage.animateTo(
            percentageAnimateTo,
            animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.Center)
        ) {
            drawArc(
                color = secondColor,
                startAngle = -90f,
                sweepAngle = 360 * 1f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.Center)
        ) {
            drawArc(
                brush = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = stringResource(R.string.percent_progress, (percentageAnimateTo * 100).toInt())
        )
    }
}