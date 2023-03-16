package com.example.dailycalories.presentation.screens.meal.meals

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val mealRepository: MealRepository,
) : ViewModel() {


    var state by mutableStateOf(MealsState())
        private set

    private var getMealsJob: Job? = null

    init {
        getMeals(date = state.date)
        getDailyNutrition()
    }

    fun onEvent(event: MealsEvent) {
        when (event) {
            is MealsEvent.ChangeDate -> {
                if (event.date == state.date) return
                Log.d("test_bug", event.date)
                getMeals(event.date)
            }
            is MealsEvent.ShowDatePickerDialog -> {
                state = state.copy(showDatePickerDialog = event.show)
            }
            is MealsEvent.DeleteMeal -> {
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
                var kCals = 0f
                val carbsJob = async {
                    carbs = meals.sumOf { it.products.sumOf { it.carbs.toDouble() } }.toFloat()
                }
                val fatJob = async {
                    fat = meals.sumOf { it.products.sumOf { it.fat.toDouble() } }.toFloat()
                }
                val proteinsJob = async {
                    proteins = meals.sumOf {
                        it.products.sumOf { it.proteins.toDouble() }
                    }.toFloat()
                }
                val kCalsJob = async {
                    kCals = meals.sumOf { it.products.sumOf { it.cals.toDouble() } }.toFloat()
                }
                awaitAll(carbsJob, fatJob, proteinsJob, kCalsJob)
                state = state.copy(
                    meals = meals,
                    carbs = carbs,
                    fat = fat,
                    cals = kCals,
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