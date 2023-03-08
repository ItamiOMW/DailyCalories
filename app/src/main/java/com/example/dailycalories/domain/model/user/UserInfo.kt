package com.example.dailycalories.domain.model.user


data class UserInfo(
    val gender: Gender,
    val goalType: GoalType,
    val activityLevel: ActivityLevel,
    val age: Int,
    val height: Int,
    val weight: Float,
    val dailyCarbs: Float,
    val dailyProteins: Float,
    val dailyFat: Float,
    val dailyKCals: Int
)
