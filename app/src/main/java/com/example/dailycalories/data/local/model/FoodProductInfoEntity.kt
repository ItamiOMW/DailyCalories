package com.example.dailycalories.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("FoodProductInfoEntityTable")
data class FoodProductInfoEntity(
    @PrimaryKey
    val id: Long,
    val kCaloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbohydratesIn100Grams: Float,
    val fatIn100Grams: Float,
)
