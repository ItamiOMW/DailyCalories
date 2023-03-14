package com.example.dailycalories.presentation.screens.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.presentation.components.CircularProgressBar
import com.example.dailycalories.presentation.components.NutrientCard
import com.example.dailycalories.presentation.theme.ui.Green
import com.example.dailycalories.presentation.theme.ui.Orange
import com.example.dailycalories.presentation.theme.ui.Pink
import com.example.dailycalories.presentation.theme.ui.Turquoise
import com.example.dailycalories.utils.getCurrentDateString
import com.example.dailycalories.utils.getTomorrowDateString
import com.example.dailycalories.utils.getYesterdayDateString


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val state = homeViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        HeaderSection(kCalsConsumed = state.cals, state.dailyCals)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        DaysSection(
            date = state.date,
            onDateChange = { date ->
                homeViewModel.onEvent(HomeEvent.ChangeDate(date))
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        NutrientsSection(
            calories = state.cals,
            proteins = state.proteins,
            carbs = state.carbs,
            fat = state.fat,
            dailyCalories = state.dailyCals,
            dailyProteins = state.dailyProteins,
            dailyCarbs = state.dailyCarbs,
            dailyFat = state.dailyFat
        )
    }

}


@Composable
private fun HeaderSection(
    kCalsConsumed: Float,
    kCalsToConsumeInTotal: Float,
) {
    val progress = remember(kCalsConsumed, kCalsToConsumeInTotal) {
        kCalsConsumed / kCalsToConsumeInTotal
    }

    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(
                text = stringResource(R.string.count_calories, 1000.0f),
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold),
            )
            Text(
                text = stringResource(R.string.text_consumed),
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colors.onSecondary,
            )
        }
        CircularProgressBar(
            percentage = progress,
            radius = 40.dp,
            strokeWidth = 6.dp,
            color = Brush.horizontalGradient(
                colors = listOf(
                    Turquoise,
                    Green,
                )
            ),
        )
    }

}

@Composable
private fun DaysSection(
    date: String,
    onDateChange: (date: String) -> Unit,
) {
    val todaysDate = remember {
        getCurrentDateString()
    }
    val yesterdaysDate = remember {
        getYesterdayDateString()
    }
    val tomorrowsDate = remember {
        getTomorrowDateString()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(rememberScrollState(1)),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        TextButton(onClick = { onDateChange(yesterdaysDate) }) {
            Text(
                text = stringResource(R.string.title_yesterday),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Medium),
                color = if (date == yesterdaysDate) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
            )
        }
        TextButton(onClick = { onDateChange(todaysDate) }) {
            Text(
                text = stringResource(R.string.title_today),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Medium),
                color = if (date == todaysDate) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
            )
        }
        TextButton(onClick = { onDateChange(tomorrowsDate) }) {
            Text(
                text = stringResource(R.string.title_tomorrow),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Medium),
                color = if (date == tomorrowsDate) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
            )
        }
    }
}


@Composable
private fun NutrientsSection(
    calories: Float,
    proteins: Float,
    carbs: Float,
    fat: Float,
    dailyCalories: Float,
    dailyProteins: Float,
    dailyCarbs: Float,
    dailyFat: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Spacer(modifier = Modifier.width(3.dp))
        NutrientCard(
            name = stringResource(id = R.string.title_proteins),
            count = proteins,
            countInTotal = dailyProteins,
            modifier = Modifier,
            progressBarColor = Pink
        )
        NutrientCard(
            name = stringResource(id = R.string.title_carbs),
            count = carbs,
            countInTotal = dailyCarbs,
            modifier = Modifier,
            progressBarColor = Green
        )
        NutrientCard(
            name = stringResource(id = R.string.title_fat),
            count = fat,
            countInTotal = dailyFat,
            modifier = Modifier,
            progressBarColor = Orange
        )
        NutrientCard(
            name = stringResource(id = R.string.title_calories),
            count = calories,
            countInTotal = dailyCalories,
            modifier = Modifier,
            progressBarColor = Turquoise
        )
        Spacer(modifier = Modifier.width(3.dp))
    }
}