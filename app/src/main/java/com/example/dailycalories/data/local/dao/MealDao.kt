package com.example.dailycalories.data.local.dao

import androidx.room.*
import com.example.dailycalories.data.local.model.MealEntity
import com.example.dailycalories.data.local.model.MealFoodProductEntity
import com.example.dailycalories.data.local.model.MealWithMealFoodProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Transaction
    @Query("SELECT * FROM MealEntityTable")
    fun getMealsWithMealFoodProducts(): Flow<List<MealWithMealFoodProducts>>


    @Transaction
    @Query("SELECT * FROM MealEntityTable WHERE date = :date")
    fun getMealsWithMealFoodProductsByDate(date: String): Flow<List<MealWithMealFoodProducts>>


    @Transaction
    @Query("SELECT * FROM MealEntityTable WHERE id = :id LIMIT 1")
    fun getMealWithMealFoodProductsById(id: Long): Flow<MealWithMealFoodProducts>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity): Long


    @Update
    suspend fun updateMeal(meal: MealEntity)


    @Query("DELETE FROM MealEntityTable WHERE id = :id")
    suspend fun deleteMeal(id: Long)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealFoodProduct(mealFoodProduct: MealFoodProductEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealFoodProducts(mealFoodProducts: List<MealFoodProductEntity>)


    @Update
    suspend fun updateMealFoodProduct(mealFoodProduct: MealFoodProductEntity)


    @Query("DELETE FROM MealFoodProductEntity WHERE id = :id")
    suspend fun deleteMealFoodProduct(id: Long)


    @Transaction
    suspend fun createMeal(meal: MealEntity, mealFoodProducts: List<MealFoodProductEntity>) {
        val mealId = insertMeal(meal)
        mealFoodProducts.forEach {
            insertMealFoodProduct(it.copy(mealId = mealId))
        }
    }


}