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

    fun navigateToWithPopUpToStartDestination(
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


    fun navigateToWithPopUpToStartDestination(
        route: String,
        startDestinationRoute: String
    ) {
        navHostController.graph.setStartDestination(startDestinationRoute)
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

    fun navigateToWithPopUpTo(
        route: String,
        popUpToRoute: String,
    ) {
        navHostController.navigate(
            route,
        ) {
            popUpTo(popUpToRoute) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

    fun navigateToWithoutPopUpTo(
        route: String,
    ) {
        navHostController.navigate(route)
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