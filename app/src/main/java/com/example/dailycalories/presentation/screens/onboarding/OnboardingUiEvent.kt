package com.example.dailycalories.presentation.screens.onboarding


sealed class OnboardingUiEvent {

    data class ShowSnackbar(val message: String): OnboardingUiEvent()

    object ShowPreviousPage: OnboardingUiEvent()

    object ShowNextPage: OnboardingUiEvent()

    object NavigateNext: OnboardingUiEvent()

}
