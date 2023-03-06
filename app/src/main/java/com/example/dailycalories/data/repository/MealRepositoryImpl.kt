package com.example.dailycalories.data.repository

import com.example.dailycalories.data.local.dao.MealDao
import com.example.dailycalories.data.mapper.toMeal
import com.example.dailycalories.data.mapper.toMealEntity
import com.example.dailycalories.data.mapper.toMealFoodProductEntity
import com.example.dailycalories.domain.model.Meal
import com.example.dailycalories.domain.model.MealFoodProduct
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

    override fun getMealById(id: Long): Flow<Meal> {
        return mealDao.getMealWithMealFoodProductsById(id).map { mealWithProducts ->
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
        mealDao.updateMeal(mealEntity)
    }

    override suspend fun removeMeal(id: Long) {
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
        val kCalories = mealFoodProduct.grams / HUNDRED_GRAMS * mealFoodProduct.kCaloriesIn100Grams
        val carbs = mealFoodProduct.grams / HUNDRED_GRAMS * mealFoodProduct.carbsIn100Grams
        val fat = mealFoodProduct.grams / HUNDRED_GRAMS * mealFoodProduct.fatIn100Grams
        val protein = mealFoodProduct.grams / HUNDRED_GRAMS * mealFoodProduct.proteinsIn100Grams
        val newMealFoodProduct = mealFoodProduct.copy(
            kCals = kCalories,
            carbs = carbs,
            fat = fat,
            proteins = protein
        ).toMealFoodProductEntity()
        mealDao.updateMealFoodProduct(newMealFoodProduct)
    }


}