package com.example.dailycalories.presentation.screens.daily_calorie_intake

sealed class DailyCalorieIntakeEvent {

    data class TextChangedProteins(val proteins: Float): DailyCalorieIntakeEvent()

    data class TextChangedFat(val fat: Float): DailyCalorieIntakeEvent()

    data class TextChangedCarbs(val carbs: Float): DailyCalorieIntakeEvent()

    object SaveNutrition: DailyCalorieIntakeEvent()

}
