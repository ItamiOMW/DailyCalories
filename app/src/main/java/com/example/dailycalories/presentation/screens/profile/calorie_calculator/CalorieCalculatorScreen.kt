package com.example.dailycalories.presentation.screens.profile.calorie_calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.presentation.components.NutritionResultDialog
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CalorieCalculatorScreen(
    viewModel: CalorieCalculatorViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {

    val state = viewModel.state

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is CalorieCalculatorUiEvent.ShowSnackbar -> {
                    launch {
                        val job = launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                uiEvent.message,
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                        delay(1000)
                        cancel()
                    }
                }
            }
        }
    }

    if (state.showResultDialog) {
        NutritionResultDialog(
            onDismiss = {
                viewModel.onEvent(CalorieCalculatorEvent.ShowResultDialog(false))
            },
            onSaveNutrition = { calories, proteins, fats, carbs ->
                viewModel.onEvent(
                    CalorieCalculatorEvent.SaveNutrition(
                        calories, proteins, fats, carbs
                    )
                )
                viewModel.onEvent(CalorieCalculatorEvent.ShowResultDialog(false))
            },
            calories = state.calories,
            proteins = state.proteins.toFloat(),
            fats = state.fats.toFloat(),
            carbs = state.carbs.toFloat()
        )
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(
                    onClick = {
                        onNavigateBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.desc_navigate_to_previous),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                Text(
                    text = stringResource(R.string.title_calorie_calculator),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                InfoSection(
                    state.gender,
                    state.ageText,
                    state.heightText,
                    state.weightText,
                    onGenderChanged = { gender ->
                        viewModel.onEvent(CalorieCalculatorEvent.GenderChanged(gender))
                    },
                    onAgeTextChanged = { age ->
                        viewModel.onEvent(CalorieCalculatorEvent.AgeTextChanged(age))
                    },
                    onHeightTextChanged = { height ->
                        viewModel.onEvent(CalorieCalculatorEvent.HeightTextChanged(height))
                    },
                    onWeightTextChanged = { weight ->
                        viewModel.onEvent(CalorieCalculatorEvent.WeightTextChanged(weight))
                    }
                )
                ActivitySection(
                    activityLevel = state.activityLevel,
                    goalType = state.goalType,
                    onActivityLevelChanged = { activityLevel ->
                        viewModel.onEvent(CalorieCalculatorEvent.ActivityLevelChanged(activityLevel))
                    },
                    onGoalTypeChanged = { goalType ->
                        viewModel.onEvent(CalorieCalculatorEvent.GoalTypeChanged(goalType))
                    }
                )
                NutritionRatioSection(
                    proteinsRatioText = state.proteinsRatioText,
                    fatsRatioText = state.fatsRatioText,
                    carbsRatioText = state.carbsRatioText,
                    onProteinsRatioTextChanged = { proteinsRatio ->
                        viewModel.onEvent(
                            CalorieCalculatorEvent.ProteinsRatioTextChanged(
                                proteinsRatio
                            )
                        )
                    },
                    onFatsRatioTextChanged = { fatsRatio ->
                        viewModel.onEvent(
                            CalorieCalculatorEvent.FatsRatioTextChanged(
                                fatsRatio
                            )
                        )
                    },
                    onCarbsRatioTextChanged = { carbsRatio ->
                        viewModel.onEvent(
                            CalorieCalculatorEvent.CarbsRatioTextChanged(
                                carbsRatio
                            )
                        )
                    }
                )
                Button(
                    onClick = {
                        viewModel.onEvent(CalorieCalculatorEvent.Calculate)
                    }
                ) {
                    Text(text = stringResource(R.string.title_calculate))
                }
            }
        }
    }
}


@Composable
private fun InfoSection(
    gender: Gender,
    ageText: String,
    heightText: String,
    weightText: String,
    onGenderChanged: (Gender) -> Unit,
    onAgeTextChanged: (age: String) -> Unit,
    onHeightTextChanged: (height: String) -> Unit,
    onWeightTextChanged: (weight: String) -> Unit,
) {

    val dropdownMenuExpanded = rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.title_gender))
            TextButton(
                onClick = {
                    dropdownMenuExpanded.value = true
                }
            ) {
                Text(text = stringResource(id = gender.nameResId))
                DropdownMenu(
                    expanded = dropdownMenuExpanded.value,
                    onDismissRequest = { dropdownMenuExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            dropdownMenuExpanded.value = false
                            onGenderChanged(Gender.Male)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_male),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            dropdownMenuExpanded.value = false
                            onGenderChanged(Gender.Female)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_female),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                modifier = Modifier.weight(1f),
                value = ageText,
                onValueChange = { ageString ->
                    onAgeTextChanged(ageString)
                },
                label = {
                    Text(
                        text = stringResource(R.string.text_age_in_years),
                        style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            TextField(
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                modifier = Modifier.weight(1f),
                value = heightText,
                onValueChange = { heightString ->
                    onHeightTextChanged(heightString)
                },
                label = {
                    Text(
                        text = stringResource(R.string.text_height_in_cm),
                        style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            TextField(
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                modifier = Modifier.weight(1f),
                value = weightText,
                onValueChange = { weightString ->
                    onWeightTextChanged(weightString)
                },
                label = {
                    Text(
                        text = stringResource(R.string.text_weight_in_kg),
                        style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
    }
}


@Composable
private fun ActivitySection(
    activityLevel: ActivityLevel,
    goalType: GoalType,
    onActivityLevelChanged: (ActivityLevel) -> Unit,
    onGoalTypeChanged: (GoalType) -> Unit,
) {

    val activityLevelDropdownMenuExpanded = rememberSaveable {
        mutableStateOf(false)
    }

    val goalDropdownMenuExpanded = rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.title_activity_level))
            TextButton(
                onClick = {
                    activityLevelDropdownMenuExpanded.value = true
                }
            ) {
                Text(text = stringResource(id = activityLevel.nameResId))
                DropdownMenu(
                    expanded = activityLevelDropdownMenuExpanded.value,
                    onDismissRequest = { activityLevelDropdownMenuExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            activityLevelDropdownMenuExpanded.value = false
                            onActivityLevelChanged(ActivityLevel.Low)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_activity_low),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            activityLevelDropdownMenuExpanded.value = false
                            onActivityLevelChanged(ActivityLevel.Medium)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_activity_medium),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            activityLevelDropdownMenuExpanded.value = false
                            onActivityLevelChanged(ActivityLevel.High)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_activity_high),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            activityLevelDropdownMenuExpanded.value = false
                            onActivityLevelChanged(ActivityLevel.SuperHigh)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_activity_superhigh),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.title_goal))
            TextButton(
                onClick = {
                    goalDropdownMenuExpanded.value = true
                }
            ) {
                Text(text = stringResource(id = goalType.nameResId))
                DropdownMenu(
                    expanded = goalDropdownMenuExpanded.value,
                    onDismissRequest = { goalDropdownMenuExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            goalDropdownMenuExpanded.value = false
                            onGoalTypeChanged(GoalType.LoseWeight)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_lose_weight),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            goalDropdownMenuExpanded.value = false
                            onGoalTypeChanged(GoalType.KeepWeight)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_keep_weight),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            goalDropdownMenuExpanded.value = false
                            onGoalTypeChanged(GoalType.GainWeight)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_gain_weight),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NutritionRatioSection(
    proteinsRatioText: String,
    fatsRatioText: String,
    carbsRatioText: String,
    onProteinsRatioTextChanged: (text: String) -> Unit,
    onFatsRatioTextChanged: (text: String) -> Unit,
    onCarbsRatioTextChanged: (text: String) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            modifier = Modifier.weight(1f),
            value = proteinsRatioText,
            onValueChange = { proteins ->
                onProteinsRatioTextChanged(proteins)
            },
            label = {
                Text(
                    text = stringResource(R.string.text_proteins_in_percentage),
                    style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            modifier = Modifier.weight(1f),
            value = fatsRatioText,
            onValueChange = { fats ->
                onFatsRatioTextChanged(fats)
            },
            label = {
                Text(
                    text = stringResource(R.string.text_fats_in_percentage),
                    style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            modifier = Modifier.weight(1f),
            value = carbsRatioText,
            onValueChange = { carbs ->
                onCarbsRatioTextChanged(carbs)
            },
            label = {
                Text(
                    text = stringResource(R.string.text_carbs_in_percentage),
                    style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }

}