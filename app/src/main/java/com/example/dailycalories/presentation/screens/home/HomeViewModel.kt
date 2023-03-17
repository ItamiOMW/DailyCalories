package com.example.dailycalories.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private var getMealsJob: Job? = null


    var state by mutableStateOf(HomeState())
        private set

    init {
        getMeals(state.date)
        getDailyNutrition()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeDate -> {
                if (event.date == state.date) return
                getMeals(date = event.date)
            }
            is HomeEvent.DeleteMeal -> {
                deleteMeal(event.meal.id)
            }
        }
    }


    private fun deleteMeal(id: Long) {
        viewModelScope.launch {
            mealRepository.deleteMeal(id)
        }
    }

    private fun getMeals(date: String) {
        getMealsJob?.cancel()
        getMealsJob = viewModelScope.launch {
            mealRepository.getMealsByDate(date).collect { meals ->
                var carbs = 0f
                var fat = 0f
                var proteins = 0f
                var calories = 0f
                val carbsJob = async {
                    carbs = meals.sumOf { it.carbs.toDouble() }.toFloat()
                }
                val fatJob = async {
                    fat = meals.sumOf { it.fat.toDouble() }.toFloat()
                }
                val proteinsJob = async {
                    proteins = meals.sumOf { it.proteins.toDouble() }.toFloat()
                }
                val kCalsJob = async {
                    calories = meals.sumOf { it.calories.toDouble() }.toFloat()
                }
                awaitAll(carbsJob, fatJob, proteinsJob, kCalsJob)
                state = state.copy(
                    meals = meals,
                    carbs = carbs,
                    fat = fat,
                    cals = calories,
                    proteins = proteins,
                    date = date
                )
            }
        }
    }

    private fun getDailyNutrition() {
        viewModelScope.launch {
            userRepository.getUserInfo().collect { userInfo ->
                val dailyKCals = userInfo.dailyCals
                val dailyProteins = userInfo.dailyProteins
                val dailyCarbs = userInfo.dailyCarbs
                val dailyFat = userInfo.dailyFat
                state = state.copy(
                    dailyCals = dailyKCals.toFloat(),
                    dailyFat = dailyFat,
                    dailyProteins = dailyProteins,
                    dailyCarbs = dailyCarbs
                )
            }
        }
    }

}