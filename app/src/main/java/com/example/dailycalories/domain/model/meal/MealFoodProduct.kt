package com.example.dailycalories.domain.model.meal

import com.example.dailycalories.utils.UNKNOWN_ID


data class MealFoodProduct(
    val id: Long = UNKNOWN_ID,
    val mealId: Long = UNKNOWN_ID,
    val name: String,
    val grams: Float,
    val cals: Float = 0f,
    val proteins: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val caloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)
