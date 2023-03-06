package com.example.dailycalories.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailycalories.data.local.dao.FoodProductInfoDao
import com.example.dailycalories.data.local.dao.MealDao
import com.example.dailycalories.data.local.model.FoodProductInfoEntity
import com.example.dailycalories.data.local.model.MealEntity
import com.example.dailycalories.data.local.model.MealFoodProductEntity

@Database(
    entities = [
        MealEntity::class,
        MealFoodProductEntity::class,
        FoodProductInfoEntity::class,
    ],
    exportSchema = false,
    version = 1
)
abstract class DailyCaloriesDatabase : RoomDatabase() {


    abstract fun foodProductInfoDao(): FoodProductInfoDao

    abstract fun mealDao(): MealDao


    companion object {

        const val DB_NAME = "daily_calories.db"

    }
}