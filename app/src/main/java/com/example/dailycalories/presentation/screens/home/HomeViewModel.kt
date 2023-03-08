package com.example.dailycalories.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository,
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set


    private var getMealsJob: Job? = null

    init {
        getMeals(state.date)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeDate -> {
                if (event.date == state.date) return
                getMeals(date = event.date)
            }
        }
    }


    private fun getMeals(date: String) {
        getMealsJob?.cancel()
        getMealsJob = viewModelScope.launch {
            mealRepository.getMeals().collect { meals ->
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
                    proteins = meals.sumOf { it.products.sumOf { it.proteins.toDouble() } }.toFloat()
                }
                val kCalsJob = async {
                    kCals = meals.sumOf { it.products.sumOf { it.kCals.toDouble() } }.toFloat()
                }
                awaitAll(carbsJob, fatJob, proteinsJob, kCalsJob)
                state = state.copy(
                    meals = meals,
                    carbs = carbs,
                    fat = fat,
                    kCals = kCals,
                    proteins = proteins,
                    date = date
                )
            }
        }
    }

}