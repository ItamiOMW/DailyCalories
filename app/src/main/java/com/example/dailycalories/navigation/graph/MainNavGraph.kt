package com.example.dailycalories.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.dailycalories.navigation.NavigationState
import com.example.dailycalories.navigation.Screen
import com.example.dailycalories.presentation.screens.home.HomeScreen
import com.example.dailycalories.presentation.screens.meals.MealsScreen
import com.example.dailycalories.presentation.screens.profile.ProfileScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNavGraph(
    navState: NavigationState
) {
    navigation(
        route = Graph.MAIN,
        startDestination = Graph.HOME
    ) {
        homeScreenNavGraph(navState = navState)
        mealsScreenNavGraph(navState = navState)
        profileScreenNavGraph(navState = navState)
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.HomeScreen.fullRoute,
        route = Graph.HOME
    ) {
        composable(
            route = Screen.HomeScreen.fullRoute
        ) {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mealsScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.MealsScreen.fullRoute,
        route = Graph.MEALS
    ) {
        composable(
            route = Screen.MealsScreen.fullRoute
        ) {
            MealsScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.ProfileScreen.fullRoute,
        route = Graph.PROFILE
    ) {
        composable(
            route = Screen.ProfileScreen.fullRoute
        ) {
            ProfileScreen()
        }

    }
}