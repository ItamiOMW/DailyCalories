package com.example.dailycalories.presentation.screens.meal.edit_meal

import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.utils.EMPTY_STRING
import com.example.dailycalories.utils.getCurrentTimeSeconds

data class EditMealState(
    val mealProducts: List<MealFoodProduct> = listOf(),
    val timeSeconds: Long = getCurrentTimeSeconds(),
    val name: String = EMPTY_STRING,
    val showEditProductWeightDialog: Boolean = false,
    val productToEdit: MealFoodProduct? = null,
    val showTimePickerDialog: Boolean = false,
)
