package com.example.dailycalories.presentation.screens.daily_calorie_intake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.model.user.UserInfo
import com.example.dailycalories.domain.repository.CalorieCounterRepository
import com.example.dailycalories.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class DailyCalorieIntakeViewModel @Inject constructor(
    private val calorieCounterRepository: CalorieCounterRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    var state by mutableStateOf(DailyCalorieIntakeState())
        private set

    private val _uiEvent = MutableSharedFlow<DailyCalorieIntakeUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    private var updateCarbsJob: Job? = null
    private var updateFatJob: Job? = null
    private var updateProteinsJob: Job? = null

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            userRepository.getUserInfo().collect {
                loadRecommendedNutrition(it)
                return@collect
            }
        }
    }

    fun onEvent(event: DailyCalorieIntakeEvent) {
        when (event) {
            is DailyCalorieIntakeEvent.TextChangedFat -> {
                state = state.copy(fat = event.fat)
                updateFatJob?.cancel()
                updateFatJob = viewModelScope.launch {
                    delay(300)
                    updateCaloriesState(
                        state.proteins, state.fat, state.carbs
                    )
                }
            }
            is DailyCalorieIntakeEvent.TextChangedCarbs -> {
                state = state.copy(carbs = event.carbs)
                updateCarbsJob?.cancel()
                updateCarbsJob = viewModelScope.launch {
                    delay(300)
                    updateCaloriesState(
                        state.proteins, state.fat, state.carbs
                    )
                }
            }
            is DailyCalorieIntakeEvent.TextChangedProteins -> {
                state = state.copy(proteins = event.proteins)
                updateProteinsJob?.cancel()
                updateProteinsJob = viewModelScope.launch {
                    delay(300)
                    updateCaloriesState(
                        state.proteins, state.fat, state.carbs
                    )
                }
            }
            is DailyCalorieIntakeEvent.SaveNutrition -> {
                saveNutrition(
                    state.carbs,
                    state.fat,
                    state.proteins,
                    state.cals
                )
            }
        }
    }

    private fun updateCaloriesState(
        proteins: Float,
        fat: Float,
        carbs: Float,
    ) {
        viewModelScope.launch {
            val calories = calorieCounterRepository.getCaloriesByNutrition(
                proteins = proteins,
                carbs = carbs,
                fat = fat
            )
            state = state.copy(cals = calories)
        }
    }

    private fun saveNutrition(
        carbs: Float,
        fat: Float,
        proteins: Float,
        calories: Int,
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val saveCaloriesJob = async {
                userRepository.saveDailyCalories(calories)
            }
            val saveCarbsJob = async {
                userRepository.saveDailyCarbs(carbs)
            }
            val saveProteinsJob = async {
                userRepository.saveDailyProteins(proteins)
            }
            val saveFatJob = async {
                userRepository.saveDailyFat(fat)
            }
            awaitAll(saveCaloriesJob, saveCarbsJob, saveProteinsJob, saveFatJob)
            state = state.copy(isLoading = false)
            _uiEvent.emit(DailyCalorieIntakeUiEvent.NutritionSaved)
        }
    }

    private fun loadRecommendedNutrition(
        userInfo: UserInfo,
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            userInfo.let {
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
                    isInitializing = false,
                    recommendedCarbs = carbs,
                    recommendedFat = fat,
                    recommendedCals = calories,
                    recommendedProteins = proteins,
                    cals = userInfo.dailyCals,
                    proteins = userInfo.dailyProteins,
                    carbs = userInfo.dailyCarbs,
                    fat = userInfo.dailyFat,
                    isLoading = false
                )
            }
        }
    }

}