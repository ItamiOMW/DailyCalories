package com.example.dailycalories.presentation.screens.profile.calorie_calculator

import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.utils.EMPTY_STRING

data class CalorieCalculatorState(
    val ageText: String = EMPTY_STRING,
    val heightText: String = EMPTY_STRING,
    val weightText: String = EMPTY_STRING,
    val activityLevel: ActivityLevel = ActivityLevel.Medium,
    val gender: Gender = Gender.Male,
    val goalType: GoalType = GoalType.KeepWeight,

    val proteinsRatioText: String = EMPTY_STRING,
    val fatsRatioText: String = EMPTY_STRING,
    val carbsRatioText: String = EMPTY_STRING,

    val showResultDialog: Boolean = false,
    val calories: Int = 0,
    val proteins: Int = 0,
    val fats: Int = 0,
    val carbs: Int = 0,
)