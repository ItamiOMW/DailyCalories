package com.example.dailycalories.data.local.room.model

import androidx.room.Embedded
import androidx.room.Relation

data class MealWithMealFoodProducts(
    @Embedded val mealEntity: MealEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "mealId",
    )
    val mealFoodProducts: List<MealFoodProductEntity>
)
