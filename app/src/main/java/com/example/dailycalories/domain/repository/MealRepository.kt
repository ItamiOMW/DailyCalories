package com.example.dailycalories.domain.repository

import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getMeals(): Flow<List<Meal>>

    fun getMealsByDate(date: String): Flow<List<Meal>>

    suspend fun getMealById(id: Long): Meal

    suspend fun getMealByIdFlow(id: Long): Flow<Meal>

    suspend fun addMeal(meal: Meal)

    suspend fun editMeal(meal: Meal)

    suspend fun deleteMeal(id: Long)

    suspend fun addMealFoodProductToMeal(mealId: Long, mealFoodProduct: MealFoodProduct)

    suspend fun deleteMealFoodProduct(mealFoodProductId: Long)

    suspend fun editMealFoodProductWeight(newGrams: Float, mealFoodProduct: MealFoodProduct)

}