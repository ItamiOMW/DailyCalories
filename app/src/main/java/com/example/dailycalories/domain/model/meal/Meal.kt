package com.example.dailycalories.domain.model.meal

import com.example.dailycalories.utils.UNKNOWN_ID


data class Meal(
    val id: Long = UNKNOWN_ID,
    val name: String,
    val date: String,
    val timeSeconds: Long,
    val calories: Float,
    val proteins: Float,
    val carbs: Float,
    val fat: Float,
    val products: List<MealFoodProduct>,
)
