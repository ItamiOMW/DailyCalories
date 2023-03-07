package com.example.dailycalories.navigation.graph


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.example.dailycalories.navigation.Screen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mealsScreenNavGraph(
    MealsScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.MealsScreen.fullRoute,
        route = Screen.MEALS_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.MealsScreen.fullRoute
        ) {
            MealsScreenContent()
        }
    }
}