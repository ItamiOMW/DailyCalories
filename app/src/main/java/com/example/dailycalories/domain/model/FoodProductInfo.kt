package com.example.dailycalories.domain.model

data class FoodProductInfo(
    val id: Long,
    val kCaloriesIn100Grams: Float,
    val proteinIn100Grams: Float,
    val carbohydratesIn100Grams: Float,
    val fatIn100Grams: Float,
)