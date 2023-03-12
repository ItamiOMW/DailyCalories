package com.example.dailycalories.presentation.screens.onboarding

import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType

sealed class OnboardingEvent {

    data class SaveAge(val age: Int) : OnboardingEvent()

    data class SaveGoal(val goal: GoalType) : OnboardingEvent()

    data class SaveWeight(val weight: Float) : OnboardingEvent()

    data class SaveHeight(val height: Int) : OnboardingEvent()

    data class SaveGender(val gender: Gender) : OnboardingEvent()

    data class SaveActivityLevel(val activityLevel: ActivityLevel) : OnboardingEvent()

    data class SaveDailyCaloriesIntake(
        val fat: Float,
        val proteins: Float,
        val carbs: Float,
        val calories: Int,
    ): OnboardingEvent()


}
