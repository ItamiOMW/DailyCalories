package com.example.dailycalories.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dailycalories.navigation.graph.mealsScreenNavGraph
import com.example.dailycalories.navigation.graph.profileScreenNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    HomeScreenContent: @Composable () -> Unit,
    MealsScreenContent: @Composable () -> Unit,
    ProfileScreenContent: @Composable () -> Unit,

) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.fullRoute,
    ) {
        composable(
            route = Screen.HomeScreen.fullRoute,
        ) {
            HomeScreenContent()
        }
        mealsScreenNavGraph(
            MealsScreenContent = MealsScreenContent
        )
        profileScreenNavGraph(
            ProfileScreenContent = ProfileScreenContent
        )
    }
}