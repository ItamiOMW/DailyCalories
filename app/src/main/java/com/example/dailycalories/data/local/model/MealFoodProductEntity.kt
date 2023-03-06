package com.example.dailycalories.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MealFoodProductEntity")
data class MealFoodProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val mealId: Long,
    val name: String,
    val grams: Float,
    val kCals: Float,
    val proteins: Float,
    val carbs: Float,
    val fat: Float,
    val kCaloriesIn100Grams: Float,
    val proteinIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)
