package com.example.dailycalories.presentation.screens.profile.daily_calorie_intake

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.utils.EMPTY_STRING
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DailyCalorieIntakeScreen(
    viewModel: DailyCalorieIntakeViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {

    val state = viewModel.state


    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    val scope = rememberCoroutineScope()

    val sheetInitialContent: @Composable (() -> Unit) = { Text(EMPTY_STRING) }

    var customSheetContent by remember { mutableStateOf(sheetInitialContent) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            customSheetContent()
        },

        ) {
        Scaffold() {
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
                        text = stringResource(R.string.title_daily_intake),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                UnderlinedTextItemWithCount(
                    text = stringResource(R.string.title_calories),
                    unitText = stringResource(R.string.text_kcal),
                    count = state.cals,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                customSheetContent = {
                                    EditNutritionSheetContent(
                                        count = state.cals.toFloat(),
                                        recommendedCount = state.recommendedCals,
                                        unitText = stringResource(R.string.unit_text_calories),
                                        titleText = stringResource(R.string.title_daily_calorie_intake),
                                        onSave = { count ->
                                            scope.launch {
                                                viewModel.onEvent(
                                                    DailyCalorieIntakeEvent.ChangedCalories(
                                                        count.toInt()
                                                    )
                                                )
                                                bottomSheetState.hide()
                                            }
                                        },
                                        onHide = {
                                            scope.launch {
                                                bottomSheetState.hide()
                                            }
                                        }
                                    )
                                }
                                bottomSheetState.show()
                            }
                        }
                        .padding(start = 10.dp, end = 10.dp)
                        .background(MaterialTheme.colors.background),
                )
                UnderlinedTextItemWithCount(
                    text = stringResource(R.string.title_proteins),
                    unitText = stringResource(R.string.text_proteins),
                    count = state.proteins.toInt(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                customSheetContent = {
                                    EditNutritionSheetContent(
                                        count = state.proteins,
                                        recommendedCount = state.recommendedProteins.toInt(),
                                        unitText = stringResource(R.string.unit_text_proteins),
                                        titleText = stringResource(R.string.text_daily_proteins_intake),
                                        onSave = { count ->
                                            scope.launch {
                                                viewModel.onEvent(
                                                    DailyCalorieIntakeEvent.ChangedProteins(
                                                        count
                                                    )
                                                )
                                                bottomSheetState.hide()
                                            }
                                        },
                                        onHide = {
                                            scope.launch {
                                                bottomSheetState.hide()
                                            }
                                        }
                                    )
                                }
                                bottomSheetState.show()
                            }
                        }
                        .padding(start = 10.dp, end = 10.dp)
                        .background(MaterialTheme.colors.background),
                )
                UnderlinedTextItemWithCount(
                    text = stringResource(R.string.title_fats),
                    unitText = stringResource(R.string.text_fats),
                    count = state.fat.toInt(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                customSheetContent = {
                                    EditNutritionSheetContent(
                                        count = state.fat,
                                        recommendedCount = state.recommendedFat.toInt(),
                                        unitText = stringResource(R.string.unit_text_fats),
                                        titleText = stringResource(R.string.text_daily_fat_intake),
                                        onSave = { count ->
                                            scope.launch {
                                                viewModel.onEvent(
                                                    DailyCalorieIntakeEvent.ChangedFats(
                                                        count
                                                    )
                                                )
                                                bottomSheetState.hide()
                                            }
                                        },
                                        onHide = {
                                            scope.launch {
                                                bottomSheetState.hide()
                                            }
                                        }
                                    )
                                }
                                bottomSheetState.show()
                            }
                        }
                        .padding(start = 10.dp, end = 10.dp)
                        .background(MaterialTheme.colors.background),
                )
                UnderlinedTextItemWithCount(
                    text = stringResource(R.string.title_carbs),
                    unitText = stringResource(R.string.text_carbs),
                    count = state.carbs.toInt(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                customSheetContent = {
                                    EditNutritionSheetContent(
                                        count = state.carbs,
                                        recommendedCount = state.recommendedCarbs.toInt(),
                                        unitText = stringResource(R.string.unit_text_carbs),
                                        titleText = stringResource(R.string.text_daily_carbs_intake),
                                        onSave = { count ->
                                            scope.launch {
                                                viewModel.onEvent(
                                                    DailyCalorieIntakeEvent.ChangedCarbs(
                                                        count
                                                    )
                                                )
                                                bottomSheetState.hide()
                                            }
                                        },
                                        onHide = {
                                            scope.launch {
                                                bottomSheetState.hide()
                                            }
                                        }
                                    )
                                }
                                bottomSheetState.show()
                            }
                        }
                        .padding(start = 10.dp, end = 10.dp)
                        .background(MaterialTheme.colors.background),
                )
            }
        }
    }

}


@Composable
private fun EditNutritionSheetContent(
    titleText: String = stringResource(R.string.title_daily_intake),
    count: Float,
    recommendedCount: Int,
    unitText: String,
    onSave: (count: Float) -> Unit,
    onHide: () -> Unit,
) {

    val countText = rememberSaveable() {
        mutableStateOf(count.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.99f)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        IconButton(
            onClick = { onHide() },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = stringResource(R.string.desc_close_nutr_dialog),
                tint = MaterialTheme.colors.onSurface
            )
        }
        Text(
            text = titleText,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onSecondary
                ),
                value = countText.value,
                onValueChange = { newCount ->
                    countText.value = newCount
                },
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.text_find_food),
                        color = MaterialTheme.colors.onSecondary.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        countText.value = EMPTY_STRING
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = stringResource(R.string.desc_clean_textfield),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }

                }
            )
            Text(
                text = unitText,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier.background(
                MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                RoundedCornerShape(15.dp)
            ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = stringResource(
                    R.string.text_recommended_intake_count_unit,
                    recommendedCount,
                    unitText
                ),
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onSave(countText.value.toFloat())
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            )
        ) {
            Text(text = stringResource(id = R.string.title_save))
        }
    }

}


@Composable
private fun UnderlinedTextItemWithCount(
    text: String,
    modifier: Modifier = Modifier,
    unitText: String,
    count: Int,
) {

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, style = MaterialTheme.typography.h6)
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.count_and_unit, count, unitText),
                    color = MaterialTheme.colors.secondary
                )
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = text)
            }
        }
        Divider()
    }


}
