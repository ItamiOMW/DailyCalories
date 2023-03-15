package com.example.dailycalories.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class NavigationState(
    val navHostController: NavHostController,
) {

    fun navigateWithPopUpToStartDestination(
        route: String,
    ) {
        navHostController.navigate(
            route,
        ) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }


    fun navigateAndSetNewStartDestination(
        route: String,
        startDestinationRoute: String,
    ) {
        navHostController.navigate(
            route,
        ) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
            restoreState = true
            launchSingleTop = true
        }
        navHostController.graph.setStartDestination(startDestinationRoute)
    }


    fun navigateTo(
        route: String,
    ) {
        navHostController.navigate(
            route,
        ) {
            navHostController.currentDestination?.id?.let { id ->
                popUpTo(id) {
                    saveState = true
                }
            }
            restoreState = true
            launchSingleTop = true
        }
    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberAnimatedNavController(),
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}