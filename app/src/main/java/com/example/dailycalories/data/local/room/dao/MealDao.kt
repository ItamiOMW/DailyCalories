package com.example.dailycalories.data.local.room.dao

import androidx.room.*
import com.example.dailycalories.data.local.room.model.MealEntity
import com.example.dailycalories.data.local.room.model.MealFoodProductEntity
import com.example.dailycalories.data.local.room.model.MealWithMealFoodProducts
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
    suspend fun getMealWithMealFoodProductsById(id: Long): MealWithMealFoodProducts

    @Transaction
    @Query("SELECT * FROM MealEntityTable WHERE id = :id LIMIT 1")
    fun getMealWithMealFoodProductsByIdFlow(id: Long): Flow<MealWithMealFoodProducts>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity): Long


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMeal(meal: MealEntity)


    @Query("DELETE FROM MealEntityTable WHERE id = :id")
    suspend fun deleteMeal(id: Long)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealFoodProduct(mealFoodProduct: MealFoodProductEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealFoodProducts(mealFoodProducts: List<MealFoodProductEntity>)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMealFoodProduct(mealFoodProduct: MealFoodProductEntity)


    @Query("DELETE FROM MealFoodProductEntity WHERE id = :id")
    suspend fun deleteMealFoodProduct(id: Long)

    @Query("DELETE FROM MealFoodProductEntity WHERE mealId = :mealId")
    suspend fun deleteMealFoodProductsByMealId(mealId: Long)


    @Transaction
    suspend fun createMeal(meal: MealEntity, mealFoodProducts: List<MealFoodProductEntity>) {
        val mealId = insertMeal(meal)
        mealFoodProducts.forEach {
            insertMealFoodProduct(it.copy(mealId = mealId))
        }
    }


}