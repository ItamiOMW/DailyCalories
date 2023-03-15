package com.example.dailycalories.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MealFoodProductEntity")
data class MealFoodProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val mealId: Long,
    val name: String,
    val grams: Float,
    val calories: Float,
    val proteins: Float,
    val carbs: Float,
    val fat: Float,
    val caloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)
