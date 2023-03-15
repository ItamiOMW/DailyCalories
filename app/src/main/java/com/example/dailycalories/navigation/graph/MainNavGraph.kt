package com.example.dailycalories.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dailycalories.navigation.NavigationState
import com.example.dailycalories.navigation.Screen
import com.example.dailycalories.presentation.screens.home.HomeScreen
import com.example.dailycalories.presentation.screens.meal.add_meal.AddMealScreen
import com.example.dailycalories.presentation.screens.meal.edit_meal.EditMealScreen
import com.example.dailycalories.presentation.screens.meal.meal_detail.MealDetailScreen
import com.example.dailycalories.presentation.screens.meal.meals.MealsScreen
import com.example.dailycalories.presentation.screens.profile.ProfileScreen
import com.example.dailycalories.utils.UNKNOWN_ID
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNavGraph(
    navState: NavigationState,
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
            MealsScreen(
                onNavigateToAddMeal = {
                    navState.navigateTo(route = Screen.AddMealScreen.fullRoute)
                },
                onNavigateToEditMeal = { id ->
                    navState.navigateTo(route = Screen.EditMealScreen.getRouteWithArgs(id))
                },
                onNavigateToMealDetail = { id ->
                    navState.navigateTo(route = Screen.MealDetailScreen.getRouteWithArgs(id))
                }
            )
        }
        composable(
            route = Screen.AddMealScreen.fullRoute,

            ) {
            AddMealScreen()
        }
        composable(
            route = Screen.EditMealScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.MEAL_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = UNKNOWN_ID
                }
            )
        ) {
            EditMealScreen()
        }
        composable(
            route = Screen.MealDetailScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.MEAL_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = UNKNOWN_ID
                }
            )
        ) {
            MealDetailScreen()
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