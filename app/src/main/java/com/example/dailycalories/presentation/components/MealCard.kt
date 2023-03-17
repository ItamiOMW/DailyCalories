package com.example.dailycalories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.presentation.theme.ui.Green
import com.example.dailycalories.presentation.theme.ui.Orange
import com.example.dailycalories.presentation.theme.ui.Pink
import com.example.dailycalories.presentation.theme.ui.Turquoise
import com.example.dailycalories.utils.formatTimeToString
import com.example.dailycalories.utils.round


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealCard(
    meal: Meal,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    background: Color = MaterialTheme.colors.surface,
    elevation: Dp = 1.dp,
    onMealClicked: (Meal) -> Unit,
    onDeleteMealClicked: (Meal) -> Unit,
    onEditMealClicked: (Meal) -> Unit,
) {

    val dropdownMenuExpanded = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        backgroundColor = background,
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        onClick = {
            onMealClicked(meal)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column() {
                    Text(
                        text = meal.name,
                        style = MaterialTheme.typography.h6
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Alarm,
                            contentDescription = stringResource(R.string.desc_icon_alarm)
                        )
                        Text(
                            text = meal.timeSeconds.formatTimeToString(),
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            dropdownMenuExpanded.value = !dropdownMenuExpanded.value
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = stringResource(R.string.desc_show_options),
                        )
                    }
                    DropdownMenu(
                        expanded = dropdownMenuExpanded.value,
                        onDismissRequest = {
                            dropdownMenuExpanded.value = false
                        }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                dropdownMenuExpanded.value = false
                                onEditMealClicked(meal)
                            }
                        ) {
                            Text(text = stringResource(R.string.title_edit))
                        }
                        DropdownMenuItem(
                            onClick = {
                                dropdownMenuExpanded.value = false
                                onDeleteMealClicked(meal)
                            }
                        ) {
                            Text(text = stringResource(R.string.title_delete))
                        }
                    }

                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NutritionCountItem(
                    name = stringResource(id = R.string.title_proteins),
                    count = meal.proteins,
                    modifier = Modifier
                        .background(Pink, RoundedCornerShape(15.dp))
                )
                NutritionCountItem(
                    name = stringResource(id = R.string.title_fats),
                    count = meal.fat,
                    modifier = Modifier
                        .background(Orange, RoundedCornerShape(15.dp))
                )
                NutritionCountItem(
                    name = stringResource(id = R.string.title_carbs),
                    count = meal.carbs,
                    modifier = Modifier
                        .background(Green, RoundedCornerShape(15.dp))
                )
            }
            NutritionCountItem(
                name = stringResource(id = R.string.title_calories),
                count = meal.calories,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Turquoise, RoundedCornerShape(15.dp))
            )
        }
    }
}

@Composable
private fun NutritionCountItem(
    name: String,
    count: Float,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = count.round(1).toString(),
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = name,
            modifier = Modifier.padding(end = 5.dp)
        )
    }
}

