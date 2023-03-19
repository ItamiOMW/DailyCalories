package com.example.dailycalories.presentation.screens.profile.daily_calorie_intake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.model.user.UserInfo
import com.example.dailycalories.domain.repository.CalorieCounterRepository
import com.example.dailycalories.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyCalorieIntakeViewModel @Inject constructor(
    private val calorieCounterRepository: CalorieCounterRepository,
    private val userRepository: UserRepository,
) : ViewModel() {


    var state by mutableStateOf(DailyCalorieIntakeState())
        private set


    private var getRecommendedNutritionJob: Job? = null

    init {
        viewModelScope.launch {
            userRepository.getUserInfo().collect { userInfo ->
                state = state.copy(
                    cals = userInfo.dailyCals,
                    proteins = userInfo.dailyProteins,
                    carbs = userInfo.dailyCarbs,
                    fat = userInfo.dailyFat,
                )
                getRecommendedNutrition(userInfo)
            }
        }
    }


    fun onEvent(event: DailyCalorieIntakeEvent) {
        when (event) {
            is DailyCalorieIntakeEvent.ChangedFats -> {
                saveFats(event.fat)
            }
            is DailyCalorieIntakeEvent.ChangedCarbs -> {
                saveCarbs(event.carbs)
            }
            is DailyCalorieIntakeEvent.ChangedProteins -> {
                saveProteins(event.proteins)
            }
            is DailyCalorieIntakeEvent.ChangedCalories -> {
                saveCalories(event.calories)
            }
        }
    }


    private fun saveCalories(
        calories: Int,
    ) {
        viewModelScope.launch {
            userRepository.saveDailyCalories(calories)
        }
    }


    private fun saveProteins(
        proteins: Float,
    ) {
        viewModelScope.launch {
            userRepository.saveDailyProteins(proteins)
        }
    }

    private fun saveFats(
        fats: Float,
    ) {
        viewModelScope.launch {
            userRepository.saveDailyFat(fats)
        }
    }


    private fun saveCarbs(
        carbs: Float,
    ) {
        viewModelScope.launch {
            userRepository.saveDailyCarbs(carbs)
        }
    }


    private fun getRecommendedNutrition(
        userInfo: UserInfo,
    ) {
        getRecommendedNutritionJob?.cancel()
        getRecommendedNutritionJob = viewModelScope.launch {
            val calories = calorieCounterRepository.calculateCalories(
                weight = userInfo.weight,
                height = userInfo.height,
                age = userInfo.age,
                activityLevel = userInfo.activityLevel,
                goalType = userInfo.goalType,
                gender = userInfo.gender
            )
            var carbs = 0f
            var proteins = 0f
            var fat = 0f
            val carbsJob = async {
                carbs = calorieCounterRepository.calculateCarbs(calories)
            }
            val proteinsJob = async {
                proteins = calorieCounterRepository.calculateProteins(calories)
            }
            val fatJob = async {
                fat = calorieCounterRepository.calculateFat(calories)
            }
            awaitAll(carbsJob, proteinsJob, fatJob)
            state = state.copy(
                recommendedCarbs = carbs,
                recommendedFat = fat,
                recommendedCals = calories,
                recommendedProteins = proteins,
            )
        }
    }

}