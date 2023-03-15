package com.example.dailycalories.presentation.screens.meal.meals

import com.example.dailycalories.domain.model.meal.Meal

sealed class MealsEvent {

    data class ChangeDate(val date: String) : MealsEvent()

    data class ShowDatePickerDialog(val show: Boolean) : MealsEvent()

    data class DeleteMeal(val meal: Meal) : MealsEvent()

}
