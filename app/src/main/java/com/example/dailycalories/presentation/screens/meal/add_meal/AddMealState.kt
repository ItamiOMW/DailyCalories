package com.example.dailycalories.presentation.screens.meal.add_meal

import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.utils.EMPTY_STRING
import com.example.dailycalories.utils.getCurrentTimeSeconds

data class AddMealState(
    val mealProducts: List<MealFoodProduct> = listOf(),
    val timeSeconds: Long = getCurrentTimeSeconds(),
    val name: String = EMPTY_STRING,
    val showEditProductWeightDialog: Boolean = false,
    val productToEdit: MealFoodProduct? = null
)
