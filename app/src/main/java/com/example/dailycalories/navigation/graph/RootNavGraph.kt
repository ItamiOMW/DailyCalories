package com.example.dailycalories.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.dailycalories.navigation.NavigationState
import com.example.dailycalories.navigation.rememberNavigationState
import com.google.accompanist.navigation.animation.AnimatedNavHost


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavGraph(
    navState: NavigationState = rememberNavigationState(),
    startGraphRoute: String,
) {

    AnimatedNavHost(
        navController = navState.navHostController,
        route = Graph.ROOT,
        startDestination = startGraphRoute,
    ) {
        onBoardingGraph(navState = navState)
        mainNavGraph(navState = navState)
    }

}

object Graph {

    //ROOT
    const val ROOT = "root_graph"

    //Main Graphs
    const val MAIN = "main_graph"
    const val ONBOARDING = "onboarding_graph"

    //Bottom Navigation Graphs
    const val PROFILE = "profile_graph"
    const val HOME = "home_graph"
    const val MEALS = "meals_graph"
}