package com.example.dailycalories.domain.model

import com.example.dailycalories.utils.UNKNOWN_ID


data class Meal(
    val id: Long = UNKNOWN_ID,
    val name: String,
    val date: String,
    val time: Int,
    val products: List<MealFoodProduct>,
)
