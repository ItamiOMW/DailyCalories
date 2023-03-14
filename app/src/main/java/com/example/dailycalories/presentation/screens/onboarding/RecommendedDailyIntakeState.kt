package com.example.dailycalories.presentation.screens.onboarding

data class RecommendedDailyIntakeState(
    val isLoading: Boolean = false,
    val recommendedCals: Int = 0,
    val recommendedProteins: Float = 0.0f,
    val recommendedCarbs: Float = 0.0f,
    val recommendedFat: Float = 0.0f,
)
