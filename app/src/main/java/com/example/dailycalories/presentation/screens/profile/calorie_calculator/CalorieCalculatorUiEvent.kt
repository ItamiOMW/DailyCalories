package com.example.dailycalories.presentation.screens.profile.calorie_calculator

sealed class CalorieCalculatorUiEvent {

    data class ShowSnackbar(val message: String): CalorieCalculatorUiEvent()

}
