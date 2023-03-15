package com.example.dailycalories.presentation.screens.home

import com.example.dailycalories.domain.model.meal.Meal

sealed class HomeEvent {

    data class ChangeDate(val date: String) : HomeEvent()

    data class DeleteMeal(val meal: Meal) : HomeEvent()

}
