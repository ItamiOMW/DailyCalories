package com.example.dailycalories.presentation.screens.meal.edit_meal

sealed class EditMealUiEvent {

    data class ShowSnackbar(val message: String) : EditMealUiEvent()

    object MealSaved : EditMealUiEvent()


}