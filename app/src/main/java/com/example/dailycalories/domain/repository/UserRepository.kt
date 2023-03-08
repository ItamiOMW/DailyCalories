package com.example.dailycalories.domain.repository

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.domain.model.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveGender(gender: Gender)

    suspend fun saveAge(age: Int)

    suspend fun saveWeight(weight: Float)

    suspend fun saveHeight(height: Int)

    suspend fun saveActivityLevel(level: ActivityLevel)

    suspend fun saveGoalType(type: GoalType)

    suspend fun saveDailyCarbs(amount: Float)

    suspend fun saveDailyProteins(amount: Float)

    suspend fun saveDailyFat(amount: Float)

    suspend fun saveDailyCalories(amount: Int)

    suspend fun getUserInfo(): Flow<UserInfo>

    suspend fun saveShouldShowOnBoarding(shouldShow: Boolean)

    suspend fun getShouldShowOnBoarding(): Flow<Boolean>

    companion object {
        val KEY_GENDER = stringPreferencesKey("gender")
        val KEY_AGE = intPreferencesKey("age")
        val KEY_WEIGHT = floatPreferencesKey("weight")
        val KEY_HEIGHT = intPreferencesKey("height")
        val KEY_ACTIVITY_LEVEL = stringPreferencesKey("activity_level")
        val KEY_GOAL_TYPE = stringPreferencesKey("goal_type")
        val KEY_CARBS = floatPreferencesKey("carbs")
        val KEY_PROTEINS = floatPreferencesKey("proteins")
        val KEY_FAT = floatPreferencesKey("fat")
        val KEY_CALORIES = intPreferencesKey("calories")
        val KEY_SHOULD_SHOW_ON_BOARDING = booleanPreferencesKey("should_show_onboarding")

    }

}