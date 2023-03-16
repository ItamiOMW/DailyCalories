package com.example.dailycalories.domain.model.food_product

data class FoodProductInfo(
    val name: String,
    val caloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)