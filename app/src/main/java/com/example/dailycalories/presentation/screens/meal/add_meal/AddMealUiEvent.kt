package com.example.dailycalories.presentation.screens.meal.add_meal

sealed class AddMealUiEvent {

    data class ShowSnackbar(val message: String) : AddMealUiEvent()

    object MealSaved : AddMealUiEvent()


}