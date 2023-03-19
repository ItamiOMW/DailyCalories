package com.example.dailycalories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.presentation.theme.ui.Green
import com.example.dailycalories.presentation.theme.ui.Orange
import com.example.dailycalories.presentation.theme.ui.Pink
import com.example.dailycalories.presentation.theme.ui.Turquoise
import com.example.dailycalories.utils.round


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealProductCard(
    product: MealFoodProduct,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    background: Color = MaterialTheme.colors.surface,
    elevation: Dp = 1.dp,
    onProductClicked: (MealFoodProduct) -> Unit,
    onDeleteProductClicked: ((MealFoodProduct) -> Unit)? = null,
    onEditProductWeightClicked: ((MealFoodProduct) -> Unit)? = null,
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
            onProductClicked(product)
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
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = stringResource(id = R.string.count_grams_short, product.grams),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface
                    )
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
                            modifier = Modifier.align(Alignment.TopEnd),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = dropdownMenuExpanded.value,
                        onDismissRequest = {
                            dropdownMenuExpanded.value = false
                        }
                    ) {
                        if (onEditProductWeightClicked != null) {
                            DropdownMenuItem(
                                onClick = {
                                    dropdownMenuExpanded.value = false
                                    onEditProductWeightClicked(product)
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.title_edit_weight),
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                        if (onDeleteProductClicked != null) {
                            DropdownMenuItem(
                                onClick = {
                                    dropdownMenuExpanded.value = false
                                    onDeleteProductClicked(product)
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.title_delete),
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
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
                    count = product.proteins,
                    modifier = Modifier
                        .background(Pink, RoundedCornerShape(15.dp))
                )
                NutritionCountItem(
                    name = stringResource(id = R.string.title_fats),
                    count = product.fat,
                    modifier = Modifier
                        .background(Orange, RoundedCornerShape(15.dp))
                )
                NutritionCountItem(
                    name = stringResource(id = R.string.title_carbs),
                    count = product.carbs,
                    modifier = Modifier
                        .background(Green, RoundedCornerShape(15.dp))
                )
            }
            NutritionCountItem(
                name = stringResource(id = R.string.title_calories),
                count = product.cals,
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

