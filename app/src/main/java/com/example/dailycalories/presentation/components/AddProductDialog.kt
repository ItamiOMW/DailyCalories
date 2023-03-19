package com.example.dailycalories.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.food_product.FoodProductInfo
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.utils.HUNDRED_GRAMS


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddProductDialog(
    productInfo: FoodProductInfo,
    modifier: Modifier = Modifier,
    onConfirm: (MealFoodProduct) -> Unit,
    onDismiss: () -> Unit,
) {

    val weightText = rememberSaveable() {
        mutableStateOf("100.0")
    }

    val calories = remember(weightText.value) {
        val weight = weightText.value.toFloatOrNull() ?: 0f
        weight / HUNDRED_GRAMS * productInfo.caloriesIn100Grams
    }

    val fat = remember(weightText.value) {
        val weight = weightText.value.toFloatOrNull() ?: 0f
        weight / HUNDRED_GRAMS * productInfo.fatIn100Grams
    }

    val proteins = remember(weightText.value) {
        val weight = weightText.value.toFloatOrNull() ?: 0f
        weight / HUNDRED_GRAMS * productInfo.proteinsIn100Grams
    }

    val carbs = remember(weightText.value) {
        val weight = weightText.value.toFloatOrNull() ?: 0f
        weight / HUNDRED_GRAMS * productInfo.carbsIn100Grams
    }

    val context = LocalContext.current


    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = modifier,
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = productInfo.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = stringResource(id = R.string.count_calories, calories),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    CountGramsAndTextItem(
                        count = proteins,
                        text = stringResource(id = R.string.title_proteins),
                    )
                    CountGramsAndTextItem(
                        count = fat,
                        text = stringResource(id = R.string.title_fats),
                    )
                    CountGramsAndTextItem(
                        count = carbs,
                        text = stringResource(id = R.string.title_carbs),
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        TextField(
                            modifier = Modifier,
                            value = weightText.value,
                            onValueChange = { newWeight ->
                                weightText.value = newWeight
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle.Default.copy(
                                color = MaterialTheme.colors.onSecondary
                            )
                        )
                    }
                    Text(
                        text = stringResource(R.string.title_grams),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .weight(1f, fill = false),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    TextButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text(text = stringResource(R.string.title_cancel))
                    }
                    TextButton(
                        onClick = {
                            if (weightText.value.isNotBlank()) {
                                onConfirm(
                                    MealFoodProduct(
                                        name = productInfo.name,
                                        grams = weightText.value.toFloatOrNull() ?: 0f,
                                        cals = calories,
                                        proteins = proteins,
                                        fat = fat,
                                        carbs = carbs,
                                        caloriesIn100Grams = productInfo.caloriesIn100Grams,
                                        proteinsIn100Grams = productInfo.proteinsIn100Grams,
                                        carbsIn100Grams = productInfo.carbsIn100Grams,
                                        fatIn100Grams = productInfo.fatIn100Grams
                                    )
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    R.string.text_specify_weight_gr,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text(text = stringResource(R.string.title_save))
                    }
                }
            }
        }
    }
}

        
        
        
        
        
        
        
        