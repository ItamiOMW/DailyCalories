package com.example.dailycalories.data.repository

import com.example.dailycalories.data.local.room.dao.MealDao
import com.example.dailycalories.data.mapper.toMeal
import com.example.dailycalories.data.mapper.toMealEntity
import com.example.dailycalories.data.mapper.toMealFoodProductEntity
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.utils.HUNDRED_GRAMS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDao: MealDao,
) : MealRepository {

    override fun getMeals(): Flow<List<Meal>> {
        return mealDao.getMealsWithMealFoodProducts().map { list ->
            list.map { mealWithProducts ->
                val entity = mealWithProducts.mealEntity
                val products = mealWithProducts.mealFoodProducts
                entity.toMeal(products)
            }
        }
    }

    override fun getMealsByDate(date: String): Flow<List<Meal>> {
        return mealDao.getMealsWithMealFoodProductsByDate(date).map { list ->
            list.map { mealWithProducts ->
                val entity = mealWithProducts.mealEntity
                val products = mealWithProducts.mealFoodProducts
                entity.toMeal(products)
            }
        }
    }

    override suspend fun getMealById(id: Long): Meal {
        val mealWithProducts = mealDao.getMealWithMealFoodProductsById(id)
        val entity = mealWithProducts.mealEntity
        val products = mealWithProducts.mealFoodProducts
        return entity.toMeal(products)
    }

    override suspend fun getMealByIdFlow(id: Long): Flow<Meal> {
        return mealDao.getMealWithMealFoodProductsByIdFlow(id) .map { mealWithProducts ->
            val entity = mealWithProducts.mealEntity
            val products = mealWithProducts.mealFoodProducts
            entity.toMeal(products)
        }
    }

    override suspend fun addMeal(meal: Meal) {
        val mealEntity = meal.toMealEntity()
        val mealFoodProductEntities = meal.products.map { it.toMealFoodProductEntity() }
        mealDao.createMeal(meal = mealEntity, mealFoodProducts = mealFoodProductEntities)
    }

    override suspend fun editMeal(meal: Meal) {
        val mealEntity = meal.toMealEntity()
        val productEntities = meal.products.map { it.toMealFoodProductEntity() }
        mealDao.deleteMealFoodProductsByMealId(meal.id)
        mealDao.insertMealFoodProducts(productEntities)
        mealDao.updateMeal(mealEntity)
    }

    override suspend fun deleteMeal(id: Long) {
        mealDao.deleteMeal(id)
    }

    override suspend fun addMealFoodProductToMeal(mealId: Long, mealFoodProduct: MealFoodProduct) {
        val mealProductEntity = mealFoodProduct.toMealFoodProductEntity().copy(mealId = mealId)
        mealDao.insertMealFoodProduct(mealProductEntity)
    }

    override suspend fun deleteMealFoodProduct(mealFoodProductId: Long) {
        mealDao.deleteMealFoodProduct(mealFoodProductId)
    }

    override suspend fun editMealFoodProductWeight(
        newGrams: Float,
        mealFoodProduct: MealFoodProduct,
    ) {
        val calories = newGrams / HUNDRED_GRAMS * mealFoodProduct.caloriesIn100Grams
        val carbs = newGrams / HUNDRED_GRAMS * mealFoodProduct.carbsIn100Grams
        val fat = newGrams / HUNDRED_GRAMS * mealFoodProduct.fatIn100Grams
        val protein = newGrams / HUNDRED_GRAMS * mealFoodProduct.proteinsIn100Grams
        val newMealFoodProduct = mealFoodProduct.copy(
            cals = calories,
            carbs = carbs,
            fat = fat,
            proteins = protein,
            grams = newGrams
        ).toMealFoodProductEntity()
        mealDao.updateMealFoodProduct(newMealFoodProduct)
    }


}