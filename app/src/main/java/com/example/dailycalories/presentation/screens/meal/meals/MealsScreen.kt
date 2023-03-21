package com.example.dailycalories.presentation.screens.meal.meals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.presentation.components.DatePickerDialog
import com.example.dailycalories.presentation.components.MealCard
import com.example.dailycalories.presentation.components.StatisticItem
import com.example.dailycalories.utils.*

@Composable
fun MealsScreen(
    viewModel: MealsViewModel = hiltViewModel(),
    onNavigateToAddMeal: (date: String) -> Unit,
    onNavigateToMealDetail: (id: Long) -> Unit,
    onNavigateToEditMeal: (id: Long) -> Unit,
) {

    val state = viewModel.state

    val lazyListState = rememberLazyListState()

    if (state.showDatePickerDialog) {
        DatePickerDialog(
            selectedDate = state.date,
            onDismiss = {
                viewModel.onEvent(MealsEvent.ShowDatePickerDialog(false))
            },
            onDateChanged = { date ->
                viewModel.onEvent(MealsEvent.ChangeDate(date))
                viewModel.onEvent(MealsEvent.ShowDatePickerDialog(false))
            }
        )
    }

    Scaffold(
        topBar = {
            HeaderSection(
                date = state.date,
                onDateChanged = { date ->
                    viewModel.onEvent(MealsEvent.ChangeDate(date))
                },
                cals = state.cals,
                proteins = state.proteins,
                carbs = state.carbs,
                fat = state.fat,
                dailyCals = state.dailyCals,
                dailyProteins = state.dailyProteins,
                dailyCarbs = state.dailyCarbs,
                dailyFat = state.dailyFat,
                onShowDatePickerDialog = {
                    viewModel.onEvent(MealsEvent.ShowDatePickerDialog(true))
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MealsSection(
                meals = state.meals,
                listState = lazyListState,
                onMealClicked = { meal ->
                    onNavigateToMealDetail(meal.id)
                },
                onDeleteMealClicked = { meal ->
                    viewModel.onEvent(MealsEvent.DeleteMeal(meal))
                },
                onEditMealClicked = { meal ->
                    onNavigateToEditMeal(meal.id)
                }
            )
            AnimatedVisibility(
                visible = !lazyListState.isScrollInProgress,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 15.dp, end = 15.dp),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    onClick = {
                        onNavigateToAddMeal(state.date)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(R.string.desc_add_meal_icon)
                    )
                }
            }
        }
    }
}


@Composable
private fun MealsSection(
    listState: LazyListState,
    meals: List<Meal>,
    onMealClicked: (Meal) -> Unit,
    onDeleteMealClicked: (Meal) -> Unit,
    onEditMealClicked: (Meal) -> Unit,
) {

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(meals, key = { it.id }) { meal ->
            MealCard(
                modifier = Modifier,
                meal = meal,
                onMealClicked = {
                    onMealClicked(meal)
                },
                onDeleteMealClicked = {
                    onDeleteMealClicked(meal)
                },
                onEditMealClicked = {
                    onEditMealClicked(meal)
                }
            )
        }
    }
}


@Composable
private fun HeaderSection(
    date: String,
    onDateChanged: (date: String) -> Unit,
    onShowDatePickerDialog: () -> Unit,
    cals: Float,
    proteins: Float,
    carbs: Float,
    fat: Float,
    dailyCals: Float,
    dailyProteins: Float,
    dailyCarbs: Float,
    dailyFat: Float,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            )
            .animateContentSize(animationSpec = tween(300)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
        ) {
            IconButton(
                onClick = {
                    onDateChanged(getDateOfDayNDaysAgo(1, date))
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.desc_get_prvs_date),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
            val dateText = when (date) {
                getCurrentDateString() -> stringResource(
                    R.string.todays_date,
                    date.formatDateWithoutDayOfWeek()
                )
                getTomorrowDateString() -> stringResource(
                    id = R.string.tomorrows_date,
                    date.formatDateWithoutDayOfWeek()
                )
                getYesterdayDateString() -> stringResource(
                    id = R.string.yesterdays_date,
                    date.formatDateWithoutDayOfWeek()
                )
                else -> date.formatDate()
            }
            TextButton(
                onClick = {
                    onShowDatePickerDialog()
                }
            ) {
                Text(
                    text = dateText,
                    color = MaterialTheme.colors.onSecondary
                )
            }
            IconButton(
                onClick = {
                    onDateChanged(getDateOfDayNDaysInAdvance(1, date))
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.desc_get_prvs_date),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            StatisticItem(
                name = stringResource(id = R.string.title_proteins),
                count = proteins,
                totalCount = dailyProteins,
                modifier = Modifier.weight(1f)
            )
            StatisticItem(
                name = stringResource(id = R.string.title_carbs),
                count = carbs,
                totalCount = dailyCarbs,
                modifier = Modifier.weight(1f)
            )
            StatisticItem(
                name = stringResource(id = R.string.title_fats),
                count = fat,
                totalCount = dailyFat,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(1.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        StatisticItem(
            name = stringResource(id = R.string.title_calories),
            count = cals,
            totalCount = dailyCals,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }

}


