package com.example.dailycalories.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.presentation.components.NutrientsCard
import com.example.dailycalories.presentation.theme.ui.DarkBlue
import com.example.dailycalories.presentation.theme.ui.Pink
import com.example.dailycalories.presentation.theme.ui.Purple
import com.example.dailycalories.utils.getCurrentDateString
import com.example.dailycalories.utils.getTomorrowDateString
import com.example.dailycalories.utils.getYesterdayDateString


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val state = homeViewModel.state

        //TODO add meals count

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        HeaderSection(kCals = state.kCals)
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        DaysSection(
            date = state.date,
            onDateChange = { date ->
                homeViewModel.onEvent(HomeEvent.ChangeDate(date))
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        NutrientsCard(
            kCals = state.kCals,
            carbs = state.carbs,
            fat = state.fat,
            proteins = state.proteins,
            contentColor = DarkBlue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Purple,
                            Pink,
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
        )
    }

}


@Composable
private fun HeaderSection(
    kCals: Float,
) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = stringResource(R.string.kCals_count, kCals),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(start = 10.dp),
        )
        Text(
            text = stringResource(R.string.text_consumed),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 10.dp),
        )
    }
}

@Composable
private fun DaysSection(
    date: String,
    onDateChange: (date: String) -> Unit,
) {
    val todaysDate = getCurrentDateString()
    val yesterdaysDate = getYesterdayDateString()
    val tomorrowsDate = getTomorrowDateString()
    Row(
        modifier = Modifier
            .fillMaxWidth()
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