package com.example.dailycalories.data.mapper

import com.example.dailycalories.data.local.room.model.MealEntity
import com.example.dailycalories.data.local.room.model.MealFoodProductEntity
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.model.meal.MealFoodProduct


fun Meal.toMealEntity() = MealEntity(
    id = id,
    name = name,
    date = date,
    timeSeconds = timeSeconds
)

fun MealEntity.toMeal(list: List<MealFoodProductEntity>) = Meal(
    id = id,
    name = name,
    date = date,
    timeSeconds = timeSeconds,
    products = list.map { it.toMealFoodProduct() },
    calories = list.sumOf { it.calories.toDouble() }.toFloat(),
    proteins = list.sumOf { it.proteins.toDouble() }.toFloat(),
    carbs = list.sumOf { it.carbs.toDouble() }.toFloat(),
    fat = list.sumOf { it.fat.toDouble() }.toFloat()
)


fun MealFoodProductEntity.toMealFoodProduct() = MealFoodProduct(
    id = id,
    mealId = mealId,
    name = name,
    grams = grams,
    kCals = calories,
    proteins = proteins,
    carbs = carbs,
    fat = fat,
    kCaloriesIn100Grams = caloriesIn100Grams,
    proteinsIn100Grams = proteinsIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)

fun MealFoodProduct.toMealFoodProductEntity() = MealFoodProductEntity(
    id = id,
    mealId = mealId,
    name = name,
    grams = grams,
    calories = kCals,
    proteins = proteins,
    carbs = carbs,
    fat = fat,
    caloriesIn100Grams = kCaloriesIn100Grams,
    proteinsIn100Grams = proteinsIn100Grams,
    carbsIn100Grams = carbsIn100Grams,
    fatIn100Grams = fatIn100Grams
)