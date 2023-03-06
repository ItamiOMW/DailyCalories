package com.example.dailycalories.domain.model

import com.example.dailycalories.utils.UNKNOWN_ID


data class MealFoodProduct(
    val id: Long = UNKNOWN_ID,
    val mealId: Long,
    val name: String,
    val grams: Float,
    val kCals: Float = 0f,
    val proteins: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val kCaloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)
