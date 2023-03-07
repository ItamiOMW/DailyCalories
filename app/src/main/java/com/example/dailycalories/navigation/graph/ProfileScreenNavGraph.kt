package com.example.dailycalories.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import com.example.dailycalories.navigation.Screen
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreenNavGraph(
    ProfileScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.ProfileScreen.fullRoute,
        route = Screen.PROFILE_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.ProfileScreen.fullRoute
        ) {
            ProfileScreenContent()
        }
    }
}