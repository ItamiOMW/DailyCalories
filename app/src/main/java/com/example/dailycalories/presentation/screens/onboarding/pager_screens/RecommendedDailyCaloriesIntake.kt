package com.example.dailycalories.presentation.screens.onboarding.pager_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.GoalType


@Composable
fun RecommendedDailyCaloriesIntake(
    fat: Float,
    proteins: Float,
    carbs: Float,
    calories: Int,
    goalType: GoalType,
    onSaveClicked: (fat: Float, proteins: Float, carbs: Float, calories: Int) -> Unit,
    onPreviousClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    onPreviousClicked()
                },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(Icons.Outlined.KeyboardArrowLeft, null)
            }
            Text(
                text = stringResource(id = R.string.title_recom_cal_intake),
                style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
        ExplanationText(goalType = goalType)
        NutritionSection(
            fat,
            proteins,
            carbs,
            calories
        )
        Text(
            text = stringResource(R.string.text_you_can_change_cal_intake_in_profile),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(10.dp)
        )
        Button(
            onClick = {
                onSaveClicked(fat, proteins, carbs, calories)
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = stringResource(R.string.title_save),
            )
        }
    }

}


@Composable
private fun ExplanationText(
    goalType: GoalType,
) {
    val text = when (goalType) {
        is GoalType.KeepWeight -> stringResource(R.string.explanation_keep_weight)
        is GoalType.LoseWeight -> stringResource(R.string.explanation_lose_weight)
        is GoalType.GainWeight -> stringResource(R.string.explanation_gain_weight)
        else -> throw Exception("Unexpected GoalType: ${goalType.type}")
    }
    Text(
        text = text,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
private fun NutritionSection(
    fat: Float,
    proteins: Float,
    carbs: Float,
    calories: Int,
) {
    Column() {
        NutritionItem(
            value = proteins,
            name = stringResource(id = R.string.title_proteins)
        )
        NutritionItem(
            value = fat,
            name = stringResource(id = R.string.title_fat)
        )
        NutritionItem(
            value = carbs,
            name = stringResource(id = R.string.title_carbs)
        )
        NutritionItem(
            value = calories.toFloat(),
            name = stringResource(id = R.string.title_calories)
        )
    }
}

@Composable
private fun NutritionItem(
    value: Float,
    name: String,
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.name_count_grams, name, value),
            style = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center),
            modifier = Modifier.padding(10.dp)
        )
    }
}