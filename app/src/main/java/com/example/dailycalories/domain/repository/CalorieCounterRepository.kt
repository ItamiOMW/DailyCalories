package com.example.dailycalories.domain.repository

import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType

interface CalorieCounterRepository {


    suspend fun calculateCalories(
        weight: Float,
        height: Int,
        age: Int,
        activityLevel: ActivityLevel,
        goalType: GoalType,
        gender: Gender,
    ): Int

    suspend fun calculateCarbs(
        dailyCalories: Int
    ): Float

    suspend fun calculateProteins(
        dailyCalories: Int
    ): Float

    suspend fun calculateFat(
        dailyCalories: Int
    ): Float


    suspend fun getCaloriesByNutrition(
        proteins: Float,
        fat: Float,
        carbs: Float
    ): Int

}