package com.example.dailycalories.presentation.screens.onboarding.pager_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
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
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.presentation.theme.ui.White


@Composable
fun ActivityLevelPagerScreen(
    activityLevel: ActivityLevel,
    onNextClicked: (ActivityLevel) -> Unit,
    onPreviousClicked: () -> Unit,
) {

    val selectedActivityLevel = rememberSaveable(key = activityLevel.type) {
        mutableStateOf(activityLevel.type)
    }

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
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Outlined.KeyboardArrowLeft, null)
            }
            Text(
                text = stringResource(id = R.string.title_activity_level),
                style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
        ActivityLevelsSection(
            selectedActivityType = selectedActivityLevel.value,
            onActivityLevelClicked = { level ->
                selectedActivityLevel.value = level.type
            }
        )
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = White,
            onClick = {
                onNextClicked(ActivityLevel.fromString(selectedActivityLevel.value))
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
private fun ActivityLevelsSection(
    selectedActivityType: String,
    onActivityLevelClicked: (ActivityLevel) -> Unit,
) {
    val levels = listOf(
        ActivityLevel.Low,
        ActivityLevel.Medium,
        ActivityLevel.High,
        ActivityLevel.SuperHigh
    )
    Column() {
        levels.forEach { level ->
            ActivityLevelItem(
                isSelected = selectedActivityType == level.type,
                activityLevel = level,
                onActivityLevelClicked = {
                    onActivityLevelClicked(level)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ActivityLevelItem(
    isSelected: Boolean,
    activityLevel: ActivityLevel,
    onActivityLevelClicked: (ActivityLevel) -> Unit,
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        backgroundColor = if (isSelected) MaterialTheme.colors.surface else Color.Transparent,
        onClick = {
            onActivityLevelClicked(activityLevel)
        },
    ) {
        Text(
            text = stringResource(id = activityLevel.nameResId),
            style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp)
        )
    }
}