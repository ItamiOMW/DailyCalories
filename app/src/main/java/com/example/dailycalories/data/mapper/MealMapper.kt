package com.example.dailycalories.data.mapper

import com.example.dailycalories.data.local.model.MealEntity
import com.example.dailycalories.data.local.model.MealFoodProductEntity
import com.example.dailycalories.domain.model.Meal
import com.example.dailycalories.domain.model.MealFoodProduct


fun Meal.toMealEntity() = MealEntity(
    id = id,
    name = name,
    date = date,
    time = time
)

fun MealEntity.toMeal(list: List<MealFoodProductEntity>) = Meal(
    id = id,
    name = name,
    date = date,
    time = time,
    products = list.map { it.toMealFoodProduct() }
)


fun MealFoodProductEntity.toMealFoodProduct() = MealFoodProduct(
    id = id,
    mealId = mealId,
    name = name,
    grams = grams,
    kCals = kCals,
    proteins = proteins,
    carbs = carbs,
    fat = fat,
    kCaloriesIn100Grams = kCaloriesIn100Grams,
    proteinsIn100Grams = proteinsIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)

fun MealFoodProduct.toMealFoodProductEntity() = MealFoodProductEntity(
    id = id,
    mealId = mealId,
    name = name,
    grams = grams,
    kCals = kCals,
    proteins = proteins,
    carbs = carbs,
    fat = fat,
    kCaloriesIn100Grams = kCaloriesIn100Grams,
    proteinsIn100Grams = proteinsIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)