package com.example.dailycalories.presentation.screens.profile.daily_calorie_intake


data class DailyCalorieIntakeState(
    val recommendedCals: Int = 0,
    val recommendedProteins: Float = 0.0f,
    val recommendedCarbs: Float = 0.0f,
    val recommendedFat: Float = 0.0f,
    val cals: Int = 0,
    val proteins: Float = 0.0f,
    val carbs: Float = 0.0f,
    val fat: Float = 0.0f,
)
