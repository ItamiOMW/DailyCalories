package com.example.dailycalories.data.repository

import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.domain.repository.CalorieCounterRepository
import javax.inject.Inject
import kotlin.math.roundToInt


class CalorieCounterRepositoryImpl @Inject constructor(

) : CalorieCounterRepository {

    override suspend fun calculateCalories(
        weight: Float,
        height: Int,
        age: Int,
        activityLevel: ActivityLevel,
        goalType: GoalType,
        gender: Gender,
    ): Int {
        //Multiply BMR by ActivityLevelMultiplier
        val activityLevelMultiplier = when (activityLevel) {
            is ActivityLevel.Low -> 1.2
            is ActivityLevel.Medium -> 1.375
            is ActivityLevel.High -> 1.55
            is ActivityLevel.SuperHigh -> 1.725
            is ActivityLevel.Unknown -> 1.375
        }
        //bmr = basal metabolic rate
        val bmr = when (gender) {
            is Gender.Female -> {
                655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age) * activityLevelMultiplier
            }
            is Gender.Male -> {
                66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age) * activityLevelMultiplier
            }
            is Gender.Unknown -> 2250.0 //Average calories
        }
        //To lose weight it's recommended to reduce 3850 calories per week OR 550 calories per day
        //To gain weight it's recommended to add 3850 calories per week OR 550 calories per day
        //To keep weight it's recommended to keep your BMR the same
        val caloriesAdjustment = when (goalType) {
            is GoalType.LoseWeight -> -550
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 550
            is GoalType.Unknown -> 0
        }

        return (bmr + caloriesAdjustment).toInt()
    }

    override suspend fun calculateCarbs(
        dailyCalories: Int,
    ): Float {
        return dailyCalories * 0.4f / 4
    }

    override suspend fun calculateProteins(
        dailyCalories: Int,
    ): Float {
        return dailyCalories * 0.3f / 4
    }

    override suspend fun calculateFat(
        dailyCalories: Int,
    ): Float {
        return dailyCalories * 0.3f / 9
    }

    override suspend fun getCaloriesByNutrition(proteins: Float, fat: Float, carbs: Float): Int {
        return ((proteins * 4) + (fat * 9) + (carbs * 4)).roundToInt()
    }


}