package com.example.dailycalories.presentation.screens.onboarding

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.domain.model.user.UserInfo
import com.example.dailycalories.domain.repository.CalorieCounterRepository
import com.example.dailycalories.domain.repository.UserRepository
import com.example.dailycalories.domain.usecase.user_info_validation.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val calorieCounterRepository: CalorieCounterRepository,
    private val validateAge: ValidateAge,
    private val validateWeight: ValidateWeight,
    private val validateHeight: ValidateHeight,
    private val validateGoal: ValidateGoal,
    private val validateActivityLevel: ValidateActivityLevel,
    private val validateGender: ValidateGender,
) : ViewModel() {


    private val _uiEvent = MutableSharedFlow<OnboardingUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var userInfoState by mutableStateOf(UserInfo.DEFAULT)
        private set

    var recommendedDailyIntakeState by mutableStateOf(RecommendedDailyIntakeState())
        private set

    private var saveJob: Job? = null

    init {
        viewModelScope.launch {
            userRepository.getUserInfo().collect { userInfo ->
                userInfoState = userInfo
            }
        }
    }


    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.SaveGoal -> {
                saveGoal(event.goal)
            }
            is OnboardingEvent.SaveHeight -> {
                saveHeight(event.height)
            }
            is OnboardingEvent.SaveWeight -> {
                saveWeight(event.weight)
            }
            is OnboardingEvent.SaveAge -> {
                saveAge(event.age)
            }
            is OnboardingEvent.SaveGender -> {
                saveGender(event.gender)
            }
            is OnboardingEvent.SaveActivityLevel -> {
                saveActivityLevel(event.activityLevel)
            }
            is OnboardingEvent.SaveDailyCaloriesIntake -> {
                saveNutrition(
                    fat = event.fat,
                    proteins = event.proteins,
                    carbs = event.carbs,
                    calories = event.calories
                )

            }
        }
    }


    private fun saveGoal(goalType: GoalType) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateGoal(goalType)
            if (result.successful) {
                userRepository.saveGoalType(goalType)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }

    private fun saveHeight(height: Int) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateHeight(height)
            if (result.successful) {
                userRepository.saveHeight(height)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }

    private fun saveWeight(weight: Float) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateWeight(weight)
            if (result.successful) {
                userRepository.saveWeight(weight)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }


    private fun saveAge(age: Int) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateAge(age)
            if (result.successful) {
                userRepository.saveAge(age)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }

    private fun saveGender(gender: Gender) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateGender(gender)
            if (result.successful) {
                userRepository.saveGender(gender)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }

    private fun saveActivityLevel(activityLevel: ActivityLevel) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            val result = validateActivityLevel(activityLevel)
            if (result.successful) {
                userRepository.saveActivityLevel(activityLevel)
                loadRecommendedNutrition(userInfoState)
                _uiEvent.emit(OnboardingUiEvent.ShowNextPage)
                return@launch
            }
            if (result.errorMessageResId != null) {
                _uiEvent.emit(OnboardingUiEvent.ShowSnackbar(application.getString(result.errorMessageResId)))
                return@launch
            }
            return@launch
        }
    }


    private fun loadRecommendedNutrition(
        userInfo: UserInfo,
    ) {
        viewModelScope.launch {
            recommendedDailyIntakeState = recommendedDailyIntakeState.copy(isLoading = true)
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
                recommendedDailyIntakeState = RecommendedDailyIntakeState(
                    recommendedProteins = proteins,
                    recommendedCals = calories,
                    recommendedFat = fat,
                    recommendedCarbs = carbs,
                    isLoading = false
                )
            }
        }
    }

    private fun saveNutrition(
        fat: Float,
        proteins: Float,
        carbs: Float,
        calories: Int,
    ) {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            recommendedDailyIntakeState = recommendedDailyIntakeState.copy(isLoading = true)
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
            userRepository.saveShouldShowOnBoarding(false)
            recommendedDailyIntakeState = recommendedDailyIntakeState.copy(isLoading = false)
            _uiEvent.emit(OnboardingUiEvent.NavigateNext)
        }
    }
}
