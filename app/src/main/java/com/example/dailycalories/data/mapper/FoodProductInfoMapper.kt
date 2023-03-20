package com.example.dailycalories.data.mapper

import com.example.dailycalories.data.remote.model.ProductDto
import com.example.dailycalories.domain.model.food_product.FoodProductInfo


fun List<ProductDto>.toListFoodProductInfo(): List<FoodProductInfo> {
    val newList = this.filter {
        it.productName != null
    }
    return newList.map {
        FoodProductInfo(
            name = it.productName?.trim()!!,
            caloriesIn100Grams = it.nutriments.energyKcal100g,
            proteinsIn100Grams = it.nutriments.proteins100g,
            fatIn100Grams = it.nutriments.fat100g,
            carbsIn100Grams = it.nutriments.carbohydrates100g,
        )
    }

}