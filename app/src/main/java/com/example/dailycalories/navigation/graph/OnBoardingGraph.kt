package com.example.dailycalories.navigation.graph


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.dailycalories.navigation.NavigationState
import com.example.dailycalories.navigation.Screen
import com.example.dailycalories.presentation.screens.onboarding.OnboardingScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onBoardingGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.OnboardingScreen.fullRoute,
        route = Graph.ONBOARDING
    ) {
        composable(
            route = Screen.OnboardingScreen.fullRoute
        ) {
            OnboardingScreen(
                onNavigateForward = {
                    navState.navigateAndSetNewStartDestination(
                        Graph.MAIN,
                        Graph.MAIN
                    )
                },
            )
        }
    }
}