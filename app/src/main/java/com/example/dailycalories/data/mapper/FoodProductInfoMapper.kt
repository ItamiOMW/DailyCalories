package com.example.dailycalories.data.mapper

import com.example.dailycalories.data.remote.model.ProductDto
import com.example.dailycalories.domain.model.FoodProductInfo


fun ProductDto.toFoodProductInfo() = FoodProductInfo(
    name = productName,
    imageFrontThumbUrl = imageFrontThumbUrl,
    kCaloriesIn100Grams = nutriments.energyKcal100g,
    proteinsIn100Grams = nutriments.proteins100g,
    fatIn100Grams = nutriments.fat100g,
    carbsIn100Grams = nutriments.energyKcal100g,
)