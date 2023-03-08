package com.example.dailycalories.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailycalories.data.local.room.dao.MealDao
import com.example.dailycalories.data.local.room.model.MealEntity
import com.example.dailycalories.data.local.room.model.MealFoodProductEntity

@Database(
    entities = [
        MealEntity::class,
        MealFoodProductEntity::class,
    ],
    exportSchema = false,
    version = 1
)
abstract class DailyCaloriesDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao


    companion object {

        const val DB_NAME = "daily_calories.db"

    }
}