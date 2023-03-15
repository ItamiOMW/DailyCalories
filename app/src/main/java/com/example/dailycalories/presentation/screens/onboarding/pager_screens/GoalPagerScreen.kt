package com.example.dailycalories.presentation.screens.onboarding.pager_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.presentation.theme.ui.White


@Composable
fun GoalPagerScreen(
    goalType: GoalType,
    onNextClicked: (GoalType) -> Unit,
) {

    val selectedGoalType = rememberSaveable(key = goalType.type) {
        mutableStateOf(goalType.type)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.title_goal),
            style = MaterialTheme.typography.h4
        )
        GoalsSection(
            selectedGoalType = selectedGoalType.value,
            onGoalClicked = { goalType ->
                selectedGoalType.value = goalType.type
            }
        )
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = White,
            onClick = {
                onNextClicked(GoalType.fromString(selectedGoalType.value))
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 10.dp)
        ) {
            Icon(
                Icons.Outlined.KeyboardArrowRight,
                stringResource(R.string.desc_navigate_to_previous)
            )
        }
    }

}

@Composable
private fun GoalsSection(
    selectedGoalType: String,
    onGoalClicked: (GoalType) -> Unit,
) {
    val goalTypes = listOf(
        GoalType.LoseWeight,
        GoalType.KeepWeight,
        GoalType.GainWeight,
    )
    Column() {
        goalTypes.forEach { goalType ->
            GoalItem(
                isSelected = goalType.type == selectedGoalType,
                goalType = goalType,
                onGoalClicked = {
                    onGoalClicked(goalType)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GoalItem(
    isSelected: Boolean,
    goalType: GoalType,
    onGoalClicked: (GoalType) -> Unit,
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        backgroundColor = if (isSelected) MaterialTheme.colors.surface else Color.Transparent,
        onClick = {
            onGoalClicked(goalType)
        },
    ) {
        Text(
            text = stringResource(id = goalType.nameResId),
            style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp)
        )
    }
}