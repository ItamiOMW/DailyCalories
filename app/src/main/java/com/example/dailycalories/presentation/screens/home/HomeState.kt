package com.example.dailycalories.presentation.screens.home

import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.utils.getCurrentDateString

data class HomeState(
    val meals: List<Meal> = emptyList(),
    val kCals: Float = 0.0f,
    val proteins: Float = 0.0f,
    val carbs: Float = 0.0f,
    val fat: Float = 0.0f,
    val date: String = getCurrentDateString(),
)
