package com.example.dailycalories.presentation.screens.home

import com.example.dailycalories.domain.model.Meal

data class HomeState(
    val meals: List<Meal>,
    val consumedKCalsToday: Float
)
