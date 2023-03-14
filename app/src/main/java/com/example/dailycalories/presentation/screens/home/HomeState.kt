package com.example.dailycalories.presentation.screens.home

import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.utils.getCurrentDateString

data class HomeState(
    val meals: List<Meal> = emptyList(),
    val date: String = getCurrentDateString(),
    //Consumed
    val cals: Float = 0.0f,
    val proteins: Float = 0.0f,
    val carbs: Float = 0.0f,
    val fat: Float = 0.0f,
    //Should be consumed in total
    val dailyCals: Float = 0.0f,
    val dailyProteins: Float = 0.0f,
    val dailyCarbs: Float = 0.0f,
    val dailyFat: Float = 0.0f,
)
