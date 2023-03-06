package com.example.dailycalories.domain.repository

import com.example.dailycalories.domain.model.Meal
import com.example.dailycalories.domain.model.MealFoodProduct
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getMeals(): Flow<List<Meal>>

    fun getMealsByDate(date: String): Flow<List<Meal>>

    fun getMealById(id: Long): Flow<Meal>

    suspend fun addMeal(meal: Meal)

    suspend fun editMeal(meal: Meal)

    suspend fun removeMeal(id: Long)

    suspend fun addMealFoodProductToMeal(mealId: Long, mealFoodProduct: MealFoodProduct)

    suspend fun deleteMealFoodProduct(mealFoodProductId: Long)

    suspend fun editMealFoodProductWeight(newGrams: Float, mealFoodProduct: MealFoodProduct)

}