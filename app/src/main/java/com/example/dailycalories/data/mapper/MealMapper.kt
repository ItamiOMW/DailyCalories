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
    protein = proteins,
    carbs = carbs,
    fat = fat,
    kCaloriesIn100Grams = kCaloriesIn100Grams,
    proteinIn100Grams = proteinIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)

fun MealFoodProduct.toMealFoodProductEntity() = MealFoodProductEntity(
    id = id,
    mealId = mealId,
    name = name,
    grams = grams,
    kCals = kCals,
    proteins = protein,
    carbs = carbs,
    fat = fat,
    kCaloriesIn100Grams = kCaloriesIn100Grams,
    proteinIn100Grams = proteinIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)